package neu.lab.autoexec.util;

import java.io.*;
import java.util.HashMap;

public class ProcessResult {
    public static void main(String[] args) throws Exception {
        File dir = new File("/Users/wangchao/Downloads/sensorbug/out/sup");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) continue;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = reader.readLine();
            HashMap<String, String> hashMap = new HashMap<>();
            StringBuilder stringBuilder;
            while (line != null) {
                if (line.contains("risk method name")) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(reader.readLine());
                    stringBuilder.append(reader.readLine());
                    stringBuilder.append(reader.readLine());
                    if (!hashMap.containsKey(line)) {
                        hashMap.put(line, stringBuilder.toString());
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            bufferedWriter.write("\n");
            for (String risk : hashMap.keySet()) {
                bufferedWriter.write("\n\n" + risk + "\n");
                bufferedWriter.write(hashMap.get(risk));
            }
            bufferedWriter.close();
        }
    }
}
