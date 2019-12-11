package neu.lab.autoexec.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class PomReader {
    private String groupId;
    private String artifactId;
    private String versionId;
    private String packaging = "jar";

    public PomReader(String pomPath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(pomPath);
        Element project = document.getRootElement();
        Element packageElement = project.element("packaging");
        if (packageElement != null) {
            packaging = packageElement.getStringValue();
        } else {
            packaging = "jar";
        }
        Element parent = project.element("parent");
        if (parent != null)
            extractCoordinate(parent);
        extractCoordinate(project);

    }

    private void extractCoordinate(Element e) {
        Element groupE = e.element("groupId");
        if (null != groupE) {
            groupId = groupE.getStringValue();
        }
        Element artifactE = e.element("artifactId");
        if (null != artifactE) {
            artifactId = artifactE.getStringValue();
        }
        Element versionE = e.element("version");
        if (null != versionE) {
            versionId = versionE.getStringValue();
        }
    }

    public String getCoordinate() {
        return getGroupId() + ":" + getArtifactId() + ":" + getVersionId();
    }

    public String getPckType() {
        return this.packaging;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

//	public static void main(String[] args) throws DocumentException {
//		new PomReader("D:\\ws\\Apache_smallTest\\commons-jxpath-1.3-src\\");
//	}
}
