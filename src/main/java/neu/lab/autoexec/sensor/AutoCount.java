package neu.lab.autoexec.sensor;

public class AutoCount extends AutoSensor {

    public AutoCount(String projectDir) {
        super(projectDir);
    }

    public String getCommand() {
        return "mvn -DresultPath=/home/wwww/sensor/out/ -Dmaven.test.skip=true neu.lab:X:1.0:countProject -e";
    }
}
