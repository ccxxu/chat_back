package club.mzwh.thread;

import club.mzwh.common.util.HttpUtil;

/**  
 * @ClassName: CloudThread
 * @Description: 异步处理上传删除
 * @author ccxxu
 * @date 2020年3月15日 
 */
public class CloudThread extends Thread {

	/**
	 * 上传之后的路径，即云服务器上的路径
	 */
	private String uploadPath;
	
	/**
	 * 当前文件的路径
	 */
	private String currentPath;
	
	public CloudThread(String uploadPath, String currentPath) {
		this.uploadPath = uploadPath;
		this.currentPath = currentPath;
	}
	
	@Override
	public void run() {
		HttpUtil.uploadFile(this.uploadPath, this.currentPath);
	}

	/**
	 * @return the uploadPath
	 */
	public String getUploadPath() {
		return uploadPath;
	}
	
	/**
	 * @param uploadPath the uploadPath to set
	 */
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	/**
	 * @return the currentPath
	 */
	public String getCurrentPath() {
		return currentPath;
	}
	
	/**
	 * @param currentPath the currentPath to set
	 */
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	
	
}
