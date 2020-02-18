package neu.lab.autoexec.sensor;

public class AutoPrintSize extends AutoSensor {

    public AutoPrintSize(String projectDir) {
        super(projectDir,"size");
    }

    @Override
    public String getCommand() {
        return "mvn package -Dmaven.test.skip=true\n" +
                "mvn -DresultPath=/root/sensor/out/ -Dmaven.test.skip=true neu.lab:X:1.0:printSize -e";
    }

}
