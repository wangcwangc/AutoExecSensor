package neu.lab.autoexec.sensor;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.dom4j.DocumentException;

import neu.lab.autoexec.util.FileSyn;
import neu.lab.autoexec.util.PomReader;

public abstract class AutoSensor {
    protected String name;
    private static String Dir = "/home/wwww/wangSensor/";
    public FileSyn donePjct;// project has done;
    public FileSyn mvnExpPjt;// project that throws exception when executes maven command
    public FileSyn notJarPjct;// record project that hasn't conflict
    public FileSyn successPjt;// record project that build success(but may be has exception caught by Maven)
    public int allTask;
    public int completeSize;
    private String projectDir;

    public String getBatPath() {
        return Dir + "decca.bat";
    }

    public String getShellPath() {
        return Dir + "multithreadsensor/" + Thread.currentThread().getName() + name + "Sensor.sh";
    }

    protected String getStateDir() {
        return Dir + "state_decca/";
    }

    public abstract String getCommand();

    public AutoSensor(String projectDir, String name) {
        this.name = name;
        this.projectDir = projectDir;
    }

    protected void readState() throws IOException {
        donePjct = new FileSyn(getStateDir(), "Project_done.txt");
        mvnExpPjt = new FileSyn(getStateDir(), "Project_throw_error.txt");
        notJarPjct = new FileSyn(getStateDir(), "Project_not_jar.txt");
        successPjt = new FileSyn(getStateDir(), "Project_build_success.txt");
    }

    private void writeState() {
        donePjct.closeOut();
        mvnExpPjt.closeOut();
        notJarPjct.closeOut();
        successPjt.closeOut();
    }

    public void autoExe(List<String> pomDirs, boolean exeByOrder) throws IOException {
        File stateDir = new File(getStateDir());
        if (!stateDir.exists()) {
            stateDir.mkdirs();
        }
        readState();
        allTask = pomDirs.size();
        completeSize = 0;
        List<String> leftProjects = new ArrayList<String>();
        for (String pomPath : pomDirs) {
            if (!donePjct.contains(path2name(pomPath))) {
                leftProjects.add(pomPath);
            } else {
                completeSize++;
            }
        }
        //多线程
        ExecutorService executor = Executors.newFixedThreadPool(8);

        if (exeByOrder) {
            for (final String pomPath : leftProjects) {
                executor.execute(new Thread(new Runnable() {
                    /**
                     * When an object implementing interface <code>Runnable</code> is used
                     * to create a thread, starting the thread causes the object's
                     * <code>run</code> method to be called in that separately executing
                     * thread.
                     * <p>
                     * The general contract of the method <code>run</code> is that it may
                     * take any action whatsoever.
                     *
                     * @see Thread#run()
                     */
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + ":" + pomPath);
                        handleProject(pomPath);
                    }
                }));
            }
            executor.shutdown();
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            while (leftProjects.size() != 0) {
                int exeIndex = (int) (leftProjects.size() * Math.random());
                String pomPath = leftProjects.get(exeIndex);
                handleProject(pomPath);
                leftProjects.remove(exeIndex);

            }
        }
        writeState();
    }

    public void autoExe(boolean exeByOrder) throws IOException {
        List<String> pomDirs = getPomDirs();
        autoExe(pomDirs, exeByOrder);
    }

    private void handleProject(String pomPath) {
        String handleResult = getProjectResult(pomPath);
        synchronized (this) {
            System.out.println(handleResult);
            donePjct.add(path2name(pomPath));
            completeSize++;
        }
    }

    protected String getProjectResult(String pomPath) {
        System.out.println("complete/all: " + completeSize + "/" + allTask);
        System.out.println("handle pom for:" + pomPath);

        StringBuilder outResult = new StringBuilder("exeResult for ");
        neu.lab.autoexec.util.FileUtil.delFolder(pomPath + "/evosuite-report");

        String projectName = path2name(pomPath);

        long startTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("start time：" + sdf.format(new Date()));
        try {
            PomReader reader = new PomReader(pomPath + "/pom.xml");
            outResult.append(reader.getCoordinate() + " ");
            //skip too long project
//            if (pomPath.startsWith("D:\\ws\\gitHub_old\\hadoop-release-3.0.0-alpha1-RC0")
//                    || pomPath.startsWith("D:\\ws\\gitHub_old\\hadoop-common-release-2.5.0-rc0")
//                    || pomPath.startsWith("D:\\ws\\gitHub_old\\flink-release-1.4.0-rc2\\")) {
//                System.out.println("skip long time project:" + pomPath);
//                outResult.append("skip-long");
//                return outResult.toString();
//            }
            //skip test
            boolean isTest = pomPath.contains("/example") || pomPath.contains("/tests");
            //TODO execute test?
            if (isTest) {
                System.out.println("skip example project:" + pomPath);
                outResult.append("skip-example");
                return outResult.toString();
            }
            //skip project has executed.
            if (!donePjct.contains(projectName) && !mvnExpPjt.contains(projectName)
                    && !notJarPjct.contains(projectName)) {
                try {
                    mvnOnePom(pomPath);
                    // success
                    successPjt.add(path2name(pomPath));
                    outResult.append("success");
                } catch (Exception e) {// failed
                    e.printStackTrace();
                    mvnExpPjt.add(path2name(pomPath));
                    outResult.append("failed");
                }
            } else {// executed
                outResult.append("executed");
            }

        } catch (DocumentException e) {// can't read pom
            System.out.println(e.getMessage());
            outResult.append(pomPath);
            outResult.append("pom-error");
        }
        System.out.println("end time：" + sdf.format(new Date()));
        long runtime = (System.currentTimeMillis() - startTime) / 1000;
        outResult.append(" " + runtime);
        return outResult.toString();
    }

    private void mvnOnePom(String pomPath) throws Exception {
        // try {
        System.out.println("exec mvn for:" + pomPath);
//        writeBat(pomPath);
        writeShell(pomPath);
//        String line = "cmd.exe /C " + getBatPath();
        String line = "sh " + getShellPath();
        System.out.println(line);
        CommandLine cmdLine = CommandLine.parse(line);
        DefaultExecutor executor = new DefaultExecutor();
        executor.execute(cmdLine);
        // } catch (Exception e) {
        // e.printStackTrace();
        // mvnExpPjt.add(path2name(pomPath));
        // }
    }

    private String path2name(String path) {
        // D:\test_apache\simple\commons-logging-1.2-src
        return path.replace(projectDir, "");
    }

    private boolean isSingle(File pomFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(pomFile));
            String line = reader.readLine();
            while (line != null) {
                if (line.contains("<modules>")) {
                    reader.close();
                    return false;
                }
                line = reader.readLine();
            }
            reader.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected List<String> getPomDirs() {
        // return AutoExeEntrance.findPomPaths(new File(getProjectDir()));
        return findPomPaths(new File(projectDir));
    }

    private List<String> findPomPaths(File father) {
        File[] children = father.listFiles();
        List<String> pomPaths = new ArrayList<String>();
        if (!father.getAbsolutePath().contains("/target/")
                && !father.getAbsolutePath().contains("/src/main/resources/")
                && !father.getAbsolutePath().contains("/src/test/")) {
            for (File child : children) {
                if (child.getName().equals("pom.xml")) {
                    pomPaths.add(father.getAbsolutePath());
                }
                if (child.isDirectory()) {
                    pomPaths.addAll(findPomPaths(child));
                }
            }
        }
        return pomPaths;
    }

//    protected void writeBat(String pomPath) throws IOException {
//        PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(getBatPath())));
//        printer.println("cd " + pomPath);
//        // printer.println("mvn -Dmaven.test.skip=true package
//        // neu.lab:conflict:1.0:detect -e");
//        printer.println(getCommand());
//        printer.close();
//    }

    private void writeShell(String pomPath) throws IOException {
        PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(getShellPath())));
        printer.println("#!/bin/bash");
        printer.println("cd " + pomPath);
        printer.println(getCommand() + ">>" + Dir + "multithreadsensor/log/" + Thread.currentThread().getName() + name + "Log.txt");
        printer.close();
    }
}
