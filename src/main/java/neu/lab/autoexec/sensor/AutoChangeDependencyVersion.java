package neu.lab.autoexec.sensor;

import neu.lab.autoexec.util.FileSyn;

import java.io.IOException;

public class AutoChangeDependencyVersion extends AutoSensor {

    public AutoChangeDependencyVersion(String projectDir) {

        super(projectDir, "change",2);
    }


    public String getCommand() {
        return "mvn clean\n" +
                "mvn -f=pom.xml -DlogFilePath=/root/sensor/out/autoChangeDependencyVersionLog/ -Dmaven.test.skip=true neu.lab:AutoChangeDependencyVersion:1.0:changeDependency -e";
    }
}
