package neu.lab.autoexec.util;

import java.io.File;

public class FileUtil {
	public static boolean moveFile(String srcFileName, String tgtDir) {  
	      
	    File srcFile = new File(srcFileName);  
	    if(!srcFile.exists() || !srcFile.isFile())   
	        return false;  
	      
	    File destDir = new File(tgtDir);  
	    if (!destDir.exists())  
	        destDir.mkdirs();  
	      
	    return srcFile.renameTo(new File(tgtDir + File.separator + srcFile.getName()));  
	}

	public static void delFolder(String folderPath) {
	        try {
	           FileUtil.delAllFile(folderPath); //删除完里面所有内容
	           String filePath = folderPath;
	           filePath = filePath.toString();
	           File myFilePath = new File(filePath);
	           myFilePath.delete(); //删除空文件夹
	        } catch (Exception e) {
	          e.printStackTrace(); 
	        }
	   }

	//删除指定文件夹下所有文件
	   //param path 文件夹完整绝对路径
	      public static boolean delAllFile(String path) {
	          boolean flag = false;
	          File file = new File(path);
	          if (!file.exists()) {
	            return flag;
	          }
	          if (!file.isDirectory()) {
	            return flag;
	          }
	          String[] tempList = file.list();
	          File temp = null;
	          for (int i = 0; i < tempList.length; i++) {
	             if (path.endsWith(File.separator)) {
	                temp = new File(path + tempList[i]);
	             } else {
	                 temp = new File(path + File.separator + tempList[i]);
	             }
	             if (temp.isFile()) {
	                temp.delete();
	             }
	             if (temp.isDirectory()) {
	                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
	                delFolder(path + "/" + tempList[i]);//再删除空文件夹
	                flag = true;
	             }
	          }
	          return flag;
	        }  
}
