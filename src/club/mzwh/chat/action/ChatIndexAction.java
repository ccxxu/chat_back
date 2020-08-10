package club.mzwh.chat.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

/**
 * 小程序入口
 * @ClassName: ChatIndexAction
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年3月14日
 */
@Controller
public class ChatIndexAction {
	
	Logger log = Logger.getLogger(ChatIndexAction.class);
	
	@RequestMapping(value = "/system/avatar")
	public ModelAndView avatar(ModelMap modelMap) throws Exception {
		return new ModelAndView("/system/avatar", modelMap);
	}
	
	@RequestMapping(value = "/system/properties")
	public ModelAndView properties(ModelMap modelMap, HttpServletRequest request) throws Exception {
		String sql = "db.collection(\"sys_user\").doc(\"i-love-life\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		modelMap.put("model", new JSONObject());
		modelMap.put("content", "");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			if (array != null && array.size()>0) {
				JSONObject o1 = array.getJSONObject(0);
				JSONArray ja = o1.getJSONArray("content");
				StringBuffer content = new StringBuffer();
				for (int i = 0; i < ja.size(); i++) {
					if (i==0) {
						content.append(ja.getString(i));
						continue;
					}
					content.append("\n"+ja.getString(i));
				}
				modelMap.put("content", content.toString());
		    	modelMap.put("model", o1);
			} 
		}
		return new ModelAndView("/system/properties", modelMap);
	}
	
	@RequestMapping(value = "/system/share")
	public ModelAndView share(ModelMap modelMap) throws Exception {
		return new ModelAndView("/system/share", modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value="/header/upload", method = RequestMethod.POST)
	public Object upload(@RequestParam("imgFile") MultipartFile file, HttpServletRequest req)
	        throws IllegalStateException, IOException {

	    // 判断文件是否为空，空则返回失败页面
	    if (file.isEmpty()) {
	        return "failed";
	    }
	    // 获取文件存储路径（绝对路径）
	    String path = Constant.UPLOAD_PATH;
	    // 获取原文件名
	    String fileName = file.getOriginalFilename();
	    fileName = "/head"+fileName.substring(fileName.lastIndexOf("."));
	    // 创建文件实例
	    File filePath = new File(path, fileName);
	    // 如果文件目录不存在，创建目录
	    if (!filePath.getParentFile().exists()) {
	        filePath.getParentFile().mkdirs();
	        System.out.println("floder success: " + filePath);
	    }
	    // 第一步，上传的中间服务器
	    file.transferTo(filePath);
	    // 第二步,从中间服务器上传到小程序云服务器
//	    String tmppath = "header/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
//	    HttpUtil.uploadFile(tmppath, path+File.separator+fileName);
//	    System.out.println("==="+tmppath);
		// 第三步，存储到云服务器数据库
//		JSONObject obj = new JSONObject();
//		obj.put("avatarUrl", HttpUtil.PREFF_HTTPS+tmppath);
//		String sql = "db.collection(\"sys_user\").doc(\"401\").update({\"data\":"+obj.toString()+"})";
//		System.out.println("sql===="+sql);
//		HttpUtil.updateDatabase(sql);
//		update(req);
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "头像上传成功!!!");
		return object;
	}  
	
	@ResponseBody
	@RequestMapping(value="/share/upload", method = RequestMethod.POST)
	public Object share(@RequestParam("imgFile") MultipartFile file, HttpServletRequest req)
	        throws IllegalStateException, IOException {

	    // 判断文件是否为空，空则返回失败页面
	    if (file.isEmpty()) {
	        return "failed";
	    }
	    // 获取文件存储路径（绝对路径）
	    String path = Constant.UPLOAD_PATH;
	    // 获取原文件名
	    String fileName = file.getOriginalFilename();
	    fileName = "/header/share2020"+fileName.substring(fileName.lastIndexOf("."));
	    // 创建文件实例
	    File filePath = new File(path, fileName);
	    // 如果文件目录不存在，创建目录
	    if (!filePath.getParentFile().exists()) {
	        filePath.getParentFile().mkdirs();
	        System.out.println("folder success: " + filePath);
	    }
	    // 第一步，上传的中间服务器
	    file.transferTo(filePath);
	    // 第二步,从中间服务器上传到小程序云服务器
	    String tmppath = "header/share2020"+fileName.substring(fileName.lastIndexOf("."));
//	    HttpUtil.uploadFile(tmppath, path+File.separator+fileName);
//	    System.out.println("==="+tmppath);
		// 第三步，存储到云服务器数据库
		JSONObject obj = new JSONObject();
		obj.put("shareUrl", HttpUtil.PREFF_HTTPS+tmppath);
		String sql = "db.collection(\"sys_user\").doc(\"i-love-life\").update({\"data\":"+obj.toString()+"})";
		HttpUtil.updateDatabase(sql);
		update(req);
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "图片上传成功！！！");
		return object;
	}  
	
	@ResponseBody
	@RequestMapping(value="/user/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest req) throws IllegalStateException, IOException {

		String nickName = req.getParameter("nickName");
		String title = req.getParameter("title");
		// 第三步，存储到云服务器数据库
		JSONObject obj = new JSONObject();
		obj.put("nickName", nickName);
		obj.put("title", title);
		String sql = "db.collection(\"sys_user\").doc(\"401\").update({\"data\":"+obj.toString()+"})";
		HttpUtil.updateDatabase(sql);
		update(req);
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "保存成功!!!");
		return object;
	}  
	
	@ResponseBody
	@RequestMapping(value="/prop/save", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public Object saveProp(HttpServletRequest req) throws IllegalStateException, IOException {
		String content = req.getParameter("content");
		String titleText = req.getParameter("titleText");
		String shareText = req.getParameter("shareText");
		String title = req.getParameter("title");
		String tip = req.getParameter("tip");
		JSONObject obj = new JSONObject();
		obj.put("titleText", titleText);
		obj.put("title", title);
		obj.put("shareText", shareText);
		obj.put("tip", tip);
		if (StringUtil.isNotNullOrEmpty(content)) {
			String[] c = content.split("\n");
			JSONArray array = JSONArray.fromObject(c);
			obj.put("content", array);
		}
		String sql = "db.collection(\"sys_user\").doc(\"i-love-life\").update({\"data\":"+obj.toString()+"})";
		HttpUtil.updateDatabase(sql);
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "保存成功!!!");
		return object;
	}  
	
	@SuppressWarnings("unchecked")
	private void update(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> sessionMap = (Map<String, Object>) session.getAttribute("sessionMap");
		sessionMap.put("username", sessionMap.get("username")+"");
		String sql = "db.collection(\"sys_user\").doc(\"401\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		sessionMap.put("model", new JSONObject());
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			if (array != null && array.size()>0) {
				sessionMap.put("model", array.getJSONObject(0));
			} 
		}
		session.setAttribute("sessionMap", sessionMap);
	}
	
}
