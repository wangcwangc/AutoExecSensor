package neu.lab.autoexec.sensor;

public class AutoSemanticsConflict extends AutoSensor {

    public AutoSemanticsConflict(String projectDir) {
        super(projectDir);
    }

    public String getCommand() {
        return "mvn -f=pom.xml -DresultPath=/home/wwww/sensor/out/ -Dappend=false -DrunTime=1 -DprintDiff=true -Dmaven.test.skip=true neu.lab:X:1.0:semanticsConflict -e";
    }
}
