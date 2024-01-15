package neu.lab.autoexec.sensor;

public class AutoDeccaDetect extends AutoSensor {
    public AutoDeccaDetect(String projectDir) {
        super(projectDir, "deccaDetect", 10);
    }

    @Override
    public String getCommand() {
        return " mvn -f=pom.xml -DignoreTestScope=true -DresultPath=/home/wc/project_result/empirical_study/empirical_study_github/ -Dmaven.test.skip=true neu.lab:decca:3.0:printRiskLevel -e";
    }
}