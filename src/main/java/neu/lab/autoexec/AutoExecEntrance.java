package neu.lab.autoexec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AutoExecEntrance {

    // TODO which directory
    public static final String projectDir = "/home/wwww/wangSensor/unzip/";

    // D:\ws\gitHub_old\
    public static void main(String[] args) throws IOException {
        //TODO which goal
        if (args[0].equals("printSize")) {
            new neu.lab.autoexec.sensor.AutoPrintSize(projectDir).autoExe(true);
        } else if (args[0].equals("detect")) {
            new neu.lab.autoexec.sensor.AutoSemanticsConflict(projectDir).autoExe(getPomPathBySize("/root/sensor/out/projectSize.txt"), true);
        } else if (args[0].equals("change")) {
            new neu.lab.autoexec.sensor.AutoChangeDependencyVersion(projectDir).autoExe(getPomPathByTestSize("/root/sensor/dataset/sortTestFileResult.txt"), true);
        } else if (args[0].equals("count")) {
            new neu.lab.autoexec.sensor.AutoCount(projectDir).autoExe(getPomPathBySize("/root/sensor/dataset/sortProject.txt"), true);
        } else if (args[0].equals("risk")) {
            new neu.lab.autoexec.sensor.AutoSemanticsRisk(projectDir).autoExe(getPomPathByTreeSize("/home/wwww/wangSensor/out/projectSize.txt"), true);
        } else if (args[0].equals("classes")) {
            new neu.lab.autoexec.sensor.AutoSemanticsClassesDuplicate(projectDir).autoExe(getPomPathByTreeSize("/home/wwww/wangSensor/out/projectSize.txt"), true);
        } else if (args[0].equals("sup")) {
            new neu.lab.autoexec.sensor.AutoSemanticsSupImpl(projectDir).autoExe(getPomPathByTreeSize("/home/wwww/wangSensor/out/projectSize.txt"), true);
        } else if (args[0].equals("addDependency")) {
            new neu.lab.autoexec.sensor.AutoAddDependency(projectDir).autoExe(getPomPathByTreeSize("/home/wwww/sensor/out/projectSize.txt"), true);
        }
    }

    private static List<String> getPomPathBySize(String sizeFile) {
        Map<Integer, List<String>> size2poms = new TreeMap<Integer, List<String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sizeFile));
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals("")) {
                    String[] pom_jar_size = line.split(" ");
                    Integer size = Integer.valueOf(pom_jar_size[2]);
                    List<String> poms = size2poms.get(size);
                    if (poms == null) {
                        poms = new ArrayList<String>();
                        size2poms.put(size, poms);
                    }
                    poms.add(pom_jar_size[0]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> sortedPomPaths = new ArrayList<String>();
        for (Integer size : size2poms.keySet()) {
            sortedPomPaths.addAll(size2poms.get(size));
        }
        return sortedPomPaths;
    }

    private static List<String> getPomPathByTreeSize(String sizeFile) {
        Map<Integer, List<String>> size2poms = new TreeMap<Integer, List<String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sizeFile));
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals("")) {
                    try {
                        String[] pom_jar_size = line.split(" ");
                        Integer size = Integer.valueOf(pom_jar_size[1]);
                        if (size < 10) {
                            line = reader.readLine();
                            continue;
                        }
                        List<String> poms = size2poms.get(size);
                        if (poms == null) {
                            poms = new ArrayList<String>();
                            size2poms.put(size, poms);
                        }
                        poms.add(pom_jar_size[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                        line = reader.readLine();
                        continue;
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> sortedPomPaths = new ArrayList<String>();
        for (Integer size : size2poms.keySet()) {
            sortedPomPaths.addAll(size2poms.get(size));
        }
        return sortedPomPaths;
    }

    private static List<String> getPomPathByTestSize(String sizeFile) {
        Map<Integer, List<String>> size2poms = new TreeMap<Integer, List<String>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sizeFile));
            String line = reader.readLine();
            while (line != null) {
                if (!line.equals("")) {
                    String[] pom_jar_test_size = line.split(" ");
                    Integer size = Integer.valueOf(pom_jar_test_size[1]);
                    if (size > 10) {
                        List<String> poms = size2poms.get(size);
                        if (poms == null) {
                            poms = new ArrayList<String>();
                            size2poms.put(size, poms);
                        }
                        poms.add(pom_jar_test_size[0]);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> sortedPomPaths = new ArrayList<String>();
        for (Integer size : size2poms.keySet()) {
            sortedPomPaths.addAll(size2poms.get(size));
        }
        return sortedPomPaths;
    }
}
