package neu.lab.autoexec.sensor;

public class AutoSemanticsSupImpl extends AutoSensor{
    public AutoSemanticsSupImpl(String projectDir) {
        super(projectDir, "semanticsSupImpl");
    }

    @Override
    public String getCommand() {
        return //"mvn package -Dmaven.test.skip=true\n" +
                "mvn -f=pom.xml -DresultPath=/root/Bsensor/out/sup/ -DignoreTestScope=true -Dmaven.test.skip=true neu.lab:X:1.0:SemanticsConflictSupImpl -e";
    }
}
