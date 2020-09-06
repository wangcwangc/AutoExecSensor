package neu.lab.autoexec.sensor;

import java.util.HashMap;

public class AutoPrintSize extends AutoSensor {

    public AutoPrintSize(String projectDir) {
        super(projectDir, "size", 8);
    }

    @Override
    public String getCommand() {
        return "mvn compile -Dmaven.test.skip=true\n" +
                "mvn -DresultPath=/home/wwww/wangSensor/out/ -Dmaven.test.skip=true neu.lab:X:1.0:printSize -e";
    }

}
