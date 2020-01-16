package neu.lab.autoexec.sensor;

public class AutoSemanticsRisk extends AutoSensor {
    public AutoSemanticsRisk(String projectDir) {
        super(projectDir, "risk");
    }

    public String getCommand() {
        return "mvn package\n" +
                "mvn -f=pom.xml -DresultPath=/home/wwww/sensor/out/ -Dmaven.test.skip=true neu.lab:X:1.0:semanticsRisk -e";
    }
}
