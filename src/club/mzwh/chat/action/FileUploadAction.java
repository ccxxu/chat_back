package club.mzwh.chat.action;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.common.util.Constant;
import club.mzwh.common.util.HttpUtil;
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.UserUtil;
import club.mzwh.user.model.UserDetailModel;


/**
* <p>版权所有:(C)2013-2020</p>
* @作者: ccx
* @日期: 2020-01-20 下午4:21:36
* @描述: [UserAction]云课内容列表
*/
@Controller
public class FileUploadAction {
	
	Logger log = Logger.getLogger(FileUploadAction.class);
	
	@RequestMapping(value = "/dbs/upload02", method = RequestMethod.GET)
	public ModelAndView uploadGet(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String tb = request.getParameter("tb");
		modelMap.put("tb", tb);
		return new ModelAndView("/dbs/upload02", modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/dbs/upload02", method = RequestMethod.POST)
	public Object upload(@RequestParam("imgFile[]") MultipartFile[] files, HttpServletRequest req)
	        throws IllegalStateException, IOException {
		UserDetailModel udm = UserUtil.getUserDetail();
		String tb = req.getParameter("tb");
		JSONArray array = new JSONArray();
		if (files != null && files.length>0) {
			for (MultipartFile file : files) {
			    // 判断文件是否为空，空则返回失败页面
			    if (file.isEmpty()) {
			        return "failed";
			    }
			    // 获取文件存储路径（绝对路径）
			    String path = Constant.UPLOAD_PATH;
			    // 获取原文件名
			    String fileName = file.getOriginalFilename();
			    String ext = fileName.substring(fileName.lastIndexOf("."));
			    String tmppath = "msg/"+StringUtil.dateToString(new Date(), "yyyyMMdd")+"/"+System.currentTimeMillis()+ext;
			    // 创建文件实例
			    File filePath = new File(path, tmppath);
			    // 如果文件目录不存在，创建目录
			    if (!filePath.getParentFile().exists()) {
			        filePath.getParentFile().mkdirs();
			        System.out.println("folder success: " + filePath);
			    }
			    // 第一步，上传的中间服务器
			    file.transferTo(filePath);
			    
			    // 第二步,从中间服务器上传到小程序云服务器jiti
		//	    HttpUtil.uploadFile("mp3_jpg/"+tmppath, path+File.separator+tmppath);
		//	    System.out.println("==="+tmppath);
			   
				// 第三步，存储到云服务器数据库
				JSONObject obj = new JSONObject();
				obj.put("name", fileName);
				obj.put("avatarUrl", "");
				obj.put("nickName", "");
				obj.put("title", "");
				if (udm != null) {
					obj.put("avatarUrl", udm.getPhotoPath());
					obj.put("nickName", udm.getName());
					obj.put("title", udm.getDuty());
				}
				obj.put("_openid", "");
				String type = "jpg";
				if (".png".equals(ext) || ".jpg".equals(ext) || ".jpeg".equals(ext)) {
					type = "jpg";
				} else if (".mp3".equals(ext) || ".m4a".equals(ext) || ".aar".equals(ext)) {
					type = "mp3";
				}
				obj.put("type", type);
				obj.put("xuhao", System.currentTimeMillis());
				obj.put("is_valid", true);
				if ("mp3".equals(type)) {
					obj.put("bl", false);
					Encoder encoder = new Encoder();        
					try {            
						MultimediaInfo m = encoder.getInfo(filePath);            
						long ls = m.getDuration();            
		//				System.out.println("此视频时长为:" + ls / 60000 + "分" + ls / 1000 + "秒！");    
						obj.put("totalTime", ls / 1000);
						obj.put("curTime", ls / 1000);
					} catch (Exception e) {            
						e.printStackTrace();        
						obj.put("totalTime", 0);
						obj.put("curTime", 0);
					}    
				}
				obj.put("fileId", HttpUtil.PREFF_FILEID+tmppath);
				obj.put("src", HttpUtil.PREFF_HTTPS+tmppath);
				obj.put("filepath", "/mp3_jpg/"+tmppath);
				obj.put("tbname", tb);
				
				array.add(obj);
			}
		}
		String sql = "db.collection(\"sys_msg\").add({\"data\": "+array.toString()+"})";
		HttpUtil.addDatabase(sql);
		
		updateData(tb);
		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "上传文件成功！！！");
		return object;
	}  
	
	private void updateData(String tb) {
		String query = "db.collection(\"sys_msg\").where({'is_valid':true,\"tbname\":\""+tb+"\"}).limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", array);
			String sql = "db.collection(\"sys_view\").doc(\""+tb+"\").update({\"data\":"+jsonObject.toString()+"})";
			HttpUtil.updateDatabase(sql);
		}
	}
	
}
