package neu.lab.autoexec.sensor;

public class AutoSemanticsClassesDuplicate extends AutoSensor {

    public AutoSemanticsClassesDuplicate(String projectDir) {
        super(projectDir, "classesDuplicate",2);
    }

    @Override
    public String getCommand() {
        return //"mvn package -Dmaven.test.skip=true\n" +
                "mvn -f=pom.xml -DresultPath=/home/wwww/Wxq/out/classes/ -DignoreTestScope=true -Dmaven.test.skip=true neu.lab:X:1.0:classDupRisk -e";
    }
}