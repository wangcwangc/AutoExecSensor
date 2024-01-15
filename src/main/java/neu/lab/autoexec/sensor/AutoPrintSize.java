package neu.lab.autoexec.sensor;

import java.util.HashMap;

public class AutoPrintSize extends AutoSensor {

    public AutoPrintSize(String projectDir) {
        super(projectDir, "size", 20);
    }

    @Override
    public String getCommand() {
        return "mvn package -Dmaven.test.skip=true >> /home/wc/project_result/empirical_study/multithreadsensor/log/package.txt\n" +
                "mvn -DresultPath=/home/wc/project_result/empirical_study/empirical_study_github/ -Dmaven.test.skip=true neu.lab:decca:3.0:printSize -e";
    }

}
