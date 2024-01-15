package neu.lab.autoexec.sensor;

public class AutoAddDependency extends AutoSensor {
    public AutoAddDependency(String projectDir) {
        super(projectDir, "addDependency",2);
    }

    @Override
    public String getCommand() {
        return "mvn -Dmaven.test.skip=true neu.lab:ResolvingMavenDependencyGraph:1.0:addDependency -e";
    }
}
