package neu.lab.autoexec.sensor;

public class AutoChangeDependencyVersion extends AutoSensor {

    public AutoChangeDependencyVersion(String projectDir) {
        super(projectDir);
    }

    public String getCommand() {
        return "mvn -f=pom.xml -DlogFilePath=/home/wwww/sensor/out/autoChangeDependencyVersionLog/ -Dmaven.test.skip=true neu.lab:AutoChangeDependencyVersion:1.0:changeDependency -e";
    }
}
