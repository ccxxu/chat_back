package club.mzwh.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 获取云服务的存储功能
 * @ClassName: FileUtil
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年3月14日
 */
public class FileUtil {
	
	public static void main(String[] args) {
		try {
//			long size = getFolderSize(new File(Constant.UPLOAD_PATH));
//			double total = 5.0*1024*1024*1024;
//			System.out.println(291896442.0/total);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void copy(String srcFile, String desFile) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
        try {
        	fis = new FileInputStream(srcFile);//创建输入流对象
            File file = new File(desFile);
            if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
            fos = new FileOutputStream(desFile); //创建输出流对象               
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度   
            while((len = fis.read(datas))!=-1) { //循环读取数据
				fos.write(datas,0,len);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			try {
				if (fis != null) {
					fis.close(); //释放资源
				}
				if (fos != null) {
					fos.close(); //释放资源
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        }
    }

	public static String formetFileSize(long file) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (file < 1024) {
			fileSizeString = df.format((double) file) + "B";
		} else if (file < 1048576) {
			fileSizeString = df.format((double) file / 1024) + "K";
		} else if (file < 1073741824) {
			fileSizeString = df.format((double) file / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) file / 1073741824) + "G";
		}
		return fileSizeString;
	}



	public static long getFolderSize(File file){
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return size;
	}
	
	public static double getCloudCombine() {
		double total = 5.0*1024*1024*1024;
		return (double) getFolderSize(new File(Constant.UPLOAD_PATH)) / total;
	}
	
}
