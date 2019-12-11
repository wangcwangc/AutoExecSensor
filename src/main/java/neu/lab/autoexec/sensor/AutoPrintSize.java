package neu.lab.autoexec.sensor;

public class AutoPrintSize extends AutoSensor{

	public AutoPrintSize(String projectDir) {
		super(projectDir);
	}

	@Override
	public String getCommand() {
		return "mvn -DresultPath=/wwww/sensor/out/ -Dmaven.test.skip=true neu.lab:X:1.0:printSize -e";
	}

}