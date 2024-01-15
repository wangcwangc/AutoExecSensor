package neu.lab.autoexec.sensor;

public class AutoSemanticsRisk extends AutoSensor {
    public AutoSemanticsRisk(String projectDir) {
        super(projectDir, "risk",2);
    }

    public String getCommand() {
        return "mvn -f=pom.xml -DuseAllJar=false -DresultPath=/home/wwww/wangSensor/out/sensor/ -DignoreTestScope=true -Dmaven.test.skip=true neu.lab:X:1.0:semanticsRisk -e";
    }
}
