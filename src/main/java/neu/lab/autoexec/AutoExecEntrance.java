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
    public static final String projectDir = "/home/wwww/sensor/unzip/";

    // D:\ws\gitHub_old\
    public static void main(String[] args) throws IOException {
        //TODO which goal
        if (args.length == 0) {
            new neu.lab.autoexec.sensor.AutoPrintSize(projectDir).autoExe(true);
        } else {
            if (args[0].equals("detect")) {
                new neu.lab.autoexec.sensor.AutoSemanticsConflict(projectDir).autoExe(getPomPathBySize("/home/wwww/sensor/out/projectSize.txt"), true);
            }
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
}
