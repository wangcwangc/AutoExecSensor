package neu.lab.autoexec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AutoExecEntrance {

    // TODO which directory
    public static final String projectDir = "D:\\ws\\gitHub_latest2\\";

    // D:\ws\gitHub_old\
    public static void main(String[] args) throws IOException {
        //TODO which goal

//				new neu.lab.autoexe.decca.AutoDebug(projectDir).autoExe(getPomPathBySize("D:\\ws_testcase\\projectSize_develop.txt"),
//						true);
//        new neu.lab.autoexec.decca.AutoDebug2(projectDir).autoExe(getPomPaths(),
//                true);
//		new neu.lab.autoexe.decca.AutoPrintSize(projectDir).autoExe(true);

        // for(String
        // pomPath:getPomPathBySize("D:\\ws_testcase\\projectSize_latest2.txt")) {
        // System.out.println(pomPath);
        // }
    }

    // public static void printExePath() {
    // String path = "";
    // for (String pomPath : findPomPaths(new File(path))) {
    // System.out.println(pomPath);
    // }
    // }

    // public static List<String> findPomPaths(File father) {
    // File[] children = father.listFiles();
    // List<String> pomPaths = new ArrayList<String>();
    // for (File child : children) {
    // if (child.getName().equals("pom.xml")) {
    // pomPaths.add(father.getAbsolutePath());
    // }
    // if (child.isDirectory()) {
    // pomPaths.addAll(findPomPaths(child));
    // }
    // }
    // return pomPaths;
    // }
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

    private static List<String> getPomPaths() {
        List<String> list = new ArrayList<String>();
        //		list.add("D:\\ws\\gitHub_new\\mqtt-spy-eclipse_paho_6\\spy-common");
        //		list.add("D:\\ws\\gitHub_new\\drill-drill-1.4.0\\exec\\rpc");
        //		list.add("D:\\ws\\gitHub_new\\maven-plugin-tools-maven-plugin-tools-3.5.1\\maven-plugin-tools-annotations");
        //		list.add("D:\\ws\\gitHub_new\\flink-release-1.4.1-rc1\\flink-metrics\\flink-metrics-core");
        //		list.add("D:\\ws\\gitHub_new\\flink-release-1.4.1-rc1\\flink-metrics\\flink-metrics-ganglia");
        //		list.add("D:\\ws\\gitHub_new\\hadoop-common-release-2.5.0\\hadoop-common-project\\hadoop-minikdc");
        //		list.add("D:\\ws\\gitHub_new\\nifi-rel-nifi-1.5.0\\nifi-nar-bundles\\nifi-spring-bundle\\nifi-spring-processors");
        //		list.add("D:\\ws\\gitHub_new\\drill-drill-1.4.0\\exec\\memory\\impl");
        //		list.add("D:\\ws\\gitHub_new\\hops-YARN-2.4.1\\hadoop-common-project\\hadoop-minikdc");
        //		list.add("D:\\ws\\gitHub_new\\storm-1.1.2\\external\\storm-kafka-monitor");
        //		list.add("D:\\ws\\gitHub_new\\continuum-continuum-1.4.3\\continuum-base\\continuum-scm");
        //		list.add("D:\\ws\\gitHub_new\\sonatype-aether-aether-1.13.1\\aether-demo");
        //		list.add("D:\\ws\\gitHub_new\\cxf-cxf-3.2.2\\distribution\\src\\main\\release\\samples\\jax_rs\\tracing_opentracing");

//		list.add("D:\\ws\\gitHub_latest2\\thingsboard-2.0.3\\common\\data");
//		list.add("D:\\ws\\gitHub_latest2\\blueflood-blueflood-2.0.0\\blueflood-core");
//		list.add("D:\\ws\\gitHub_latest2\\mqtt-spy-mqtt-spy_v0.5.4\\spy-common");
//		list.add("D:\\ws\\gitHub_latest2\\webcam-capture-webcam-capture-parent-0.3.12\\webcam-capture-drivers\\driver-openimaj");
//		list.add("D:\\ws\\gitHub_latest2\\vertx-swagger-1.6.0\\sample\\petstore-vertx-server");
//		list.add("D:\\ws\\gitHub_latest2\\vertx-swagger-1.6.0\\sample\\petstore-vertx-json-server");
//		list.add("D:\\ws\\gitHub_latest2\\vertx-swagger-1.6.0\\sample\\petstore-vertx-json-rx-server");

        list.add("D:\\ws\\gitHub_latest3\\st-js-stjs-3.3.0\\command-line");
        list.add("D:\\ws\\gitHub_latest3\\styx-styx-0.7.7\\support\\api-testsupport");
        list.add("D:\\ws\\gitHub_latest3\\selendroid-0.17.0\\selendroid-client");
        list.add("D:\\ws\\gitHub_latest3\\infinitest-VERSION5.1.116\\infinitest-runner");
        list.add("D:\\ws\\gitHub_latest3\\vertx-zero-0.4.7.1\\vertx-gaia\\vertx-co");
        list.add("D:\\ws\\gitHub_latest3\\alexa-skills-kit-sdk-for-java-2.4.0\\ask-sdk");
        list.add("D:\\ws\\gitHub_latest3\\gramtest-0.2.2");
        list.add("D:\\ws\\gitHub_latest3\\Saturn-3.1.0\\saturn-core");
        list.add("D:\\ws\\gitHub_latest3\\alexa-skills-kit-sdk-for-java-2.4.0\\ask-sdk-dynamodb-persistence-adapter");
        list.add("D:\\ws\\gitHub_latest3\\styx-styx-0.7.7\\system-tests\\e2e-testsupport");
        list.add("D:\\ws\\gitHub_latest3\\acai-acai-1.1");
        list.add("D:\\ws\\gitHub_latest3\\selendroid-0.17.0\\selendroid-standalone");

//		list.add("D:\\ws\\gitHub_develop\\googleads-java-lib-master\\modules\\ads_lib");
//		list.add(
//				"D:\\ws\\gitHub_develop\\azure-storage-java-master\\microsoft-azure-storage-samples\\src\\com\\microsoft\\azure\\storage\\logging");
//		list.add("D:\\ws\\gitHub_develop\\github-api-master");
//		list.add("D:\\ws\\gitHub_develop\\swagger-parser-master\\modules\\swagger-compat-spec-parser");
//		list.add("D:\\ws\\gitHub_develop\\azure-storage-java-master\\microsoft-azure-storage-samples");
//		list.add("D:\\ws\\gitHub_develop\\alexa-skills-kit-sdk-for-java-2.0.x\\ask-sdk-dynamodb-persistence-adapter");
//		list.add("D:\\ws\\gitHub_develop\\webmagic-master\\webmagic-selenium");
//		list.add("D:\\ws\\gitHub_develop\\kurento-java-master\\kurento-repository\\kurento-repository-server");
//		list.add("D:\\ws\\gitHub_develop\\alexa-skills-kit-sdk-for-java-2.0.x\\samples\\colorpicker");
//		list.add("D:\\ws\\gitHub_develop\\jetbrick-template-2x-master\\jetbrick-template-jetbrickmvc");

        return list;
    }
}
