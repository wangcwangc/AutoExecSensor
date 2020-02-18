package neu.lab.autoexec.sensor;

public class AutoSemanticsRisk extends AutoSensor {
    public AutoSemanticsRisk(String projectDir) {
        super(projectDir, "risk");
    }

    public String getCommand() {
        return "mvn package -Dmaven.test.skip=true\n" +
                "mvn -f=pom.xml -DresultPath=/root/sensor/out/sensor/ -Dmaven.test.skip=true neu.lab:X:1.0:semanticsRisk -e";
    }
}
