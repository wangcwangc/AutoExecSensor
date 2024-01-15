package neu.lab.autoexec.sensor;

public class AutoCVEDetect extends AutoSensor {
    public AutoCVEDetect(String projectDir) {
        super(projectDir, "cveDetect", 2);
    }

    @Override
    public String getCommand() {
        return " mvn -f=pom.xml -DignoreTestScope=true -DresultPath=/home/wc/detect/result/ -Dmaven.test.skip=true xmu:CVE-Detect:1.0:detectCVE -e    ";
    }
}
