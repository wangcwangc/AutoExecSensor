package neu.lab.autoexec.sensor;

public class AutoSemanticsSupImpl extends AutoSensor{
    public AutoSemanticsSupImpl(String projectDir) {
        super(projectDir, "semanticsSupImpl",2);
    }

    @Override
    public String getCommand() {
        return //"mvn package -Dmaven.test.skip=true\n" +
                "mvn -f=pom.xml -DuseAllJar=false -DresultPath=/home/wwww/wangSensor/out/sup/ -DignoreTestScope=true -Dmaven.test.skip=true neu.lab:X:1.0:SemanticsConflictSupImpl -e";
    }
}
