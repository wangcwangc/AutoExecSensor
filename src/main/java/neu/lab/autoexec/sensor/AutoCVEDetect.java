package neu.lab.autoexec.sensor;

public class AutoCVEDetect extends AutoSensor {
    public AutoCVEDetect(String projectDir, String name, int coreNum) {
        super(projectDir, name, coreNum);
    }

    @Override
    public String getCommand() {
        return null;
    }
}
