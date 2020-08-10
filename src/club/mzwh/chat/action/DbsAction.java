package club.mzwh.chat.action;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import club.mzwh.common.util.TableDataInfo;
import club.mzwh.common.util.UserUtil;
import club.mzwh.user.model.UserDetailModel;


/**
* <p>版权所有:(C)2013-2020</p>
* @作者: ccx
* @日期: 2020-01-20 下午4:21:36
* @描述: [UserAction]云课内容列表
*/
@Controller
public class DbsAction {
	Logger log = Logger.getLogger(DbsAction.class);
	
	@RequestMapping(value = "/dbs/index", method = RequestMethod.GET)
	public ModelAndView index(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String query = "db.collection(\"sys_view\").doc(\"sys_index\").get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		JSONArray json = JSONArray.fromObject(o);
		List<JSONObject> list = new ArrayList<JSONObject>();
		JSONArray array = JSONArray.fromObject(json.getJSONObject(0).get("msg"));
		
		for (int i = 0; i < array.size(); i++) {
			list.add(array.getJSONObject(i));			
		}	
		modelMap.put("tblist", list);
		return new ModelAndView("/dbs/index", modelMap);
	}
	
	@RequestMapping(value = "/dbs/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String tb = request.getParameter("tb");
		modelMap.put("tb", tb);
		return new ModelAndView("/dbs/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/dbs/list", method = RequestMethod.POST)
	public TableDataInfo listPost(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String tb = request.getParameter("tb");
		String page = request.getParameter("pageNum");
		String rows = request.getParameter("pageSize");
		int pageSize = Integer.parseInt(rows);
		int pageNum = Integer.parseInt(page);
		int startRecord = (pageNum-1)*pageSize;
		String query = "db.collection(\"sys_msg\").where({\"tbname\":\""+tb+"\"}).orderBy('xuhao', 'asc').limit("+pageSize+").skip("+startRecord+").get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		TableDataInfo tdi = new TableDataInfo();
		JSONArray array = JSONArray.fromObject(obj.get("data"));
		List<JSONObject> list = new ArrayList<JSONObject>();
		for (int i = 0; i < array.size(); i++) {
			JSONObject jo = array.getJSONObject(i);
			list.add(jo);			
		}		
		JSONObject obj2 = JSONObject.fromObject(obj.get("pager"));
		tdi.setTotal(Long.parseLong(obj2.get("Total")+""));
		tdi.setRows(list);
		return tdi;
	}
	
	@RequestMapping(value = "/dbs/add", method = RequestMethod.GET)
	public ModelAndView addGet(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String tb = request.getParameter("tb");
		modelMap.put("tb", tb);
		return new ModelAndView("/dbs/add", modelMap);
	}
	
	@RequestMapping(value = "/dbs/upload", method = RequestMethod.GET)
	public ModelAndView uploadGet(HttpServletRequest request, ModelMap modelMap) throws Exception {
		String tb = request.getParameter("tb");
		String type = request.getParameter("type");
		modelMap.put("tb", tb);
		modelMap.put("type", type);
		return new ModelAndView("/dbs/upload", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/dbs/edit", method = RequestMethod.GET)
    public ModelAndView edit(String id, HttpServletRequest request, ModelMap mmap) {
    	String tb = request.getParameter("tb");
    	String et = request.getParameter("et");
    	String sql = "db.collection(\"sys_msg\").doc(\""+id+"\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		mmap.put("model", new JSONObject());
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			if (array != null && array.size()>0) {
				mmap.put("model", array.getJSONObject(0));
			} 
		}
		mmap.put("et", et);
		mmap.put("tb", tb);
        return new ModelAndView("/dbs/edit", mmap);
    }
	
	@ResponseBody
	@RequestMapping(value = "/dbs/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String sqlQuery = "db.collection(\"sys_user\").doc(\"401\").get()";
		String result = HttpUtil.getAllDatabases(sqlQuery);
		JSONObject jsonObject = JSONObject.fromObject(result);
		Object o = jsonObject.get("data");
		String avatarUrl = "";
		String nickName = "";
		String title = "";
		if (StringUtil.isNotNullOrEmpty(o)) {
			if (o instanceof JSONObject) {
				JSONObject json = (JSONObject) o;
				avatarUrl = json.getString("avatarUrl");
				nickName = json.getString("nickName");
				title = json.getString("title");
			}
		}
		String id = request.getParameter("_id");
		String content = request.getParameter("content");
		if (StringUtil.isNotNullOrEmpty(content)) {
			if (content.indexOf("\"")>-1) {
				content = content.replaceAll("\"", "'");
			}
		}
		String orderno = request.getParameter("xuhao");
		String isValid = request.getParameter("is_valid");
		String tb = request.getParameter("tb");
		
		long no = 0;
		try {
			no = Long.parseLong(orderno);
		} catch (NumberFormatException e) {
			no = System.currentTimeMillis();
		}
		
		if ("".equals(id) || id==null) {
			JSONObject obj = new JSONObject();
			obj.put("content", content);
			obj.put("avatarUrl", avatarUrl);
			obj.put("nickName", nickName);
			obj.put("title", title);
			obj.put("type", "txt");
			obj.put("xuhao", no);
			obj.put("is_valid", true);
			obj.put("tbname", tb);
			String sql = "db.collection(\"sys_msg\").add({\"data\": "+obj.toString()+"})";
			System.out.println(sql);
			HttpUtil.addDatabase(sql);
		} else {
			JSONObject obj = new JSONObject();
			obj.put("avatarUrl", avatarUrl);
			obj.put("nickName", nickName);
			obj.put("title", title);
			if (!("".equals(content) || content == null)) {
				obj.put("content", content);
			}
			if (!("".equals(orderno) || orderno == null)) {
				long no1 = 0;
				try {
					no1 = Long.parseLong(orderno);
				} catch (NumberFormatException e) {
					no1 = System.currentTimeMillis();
				}
				obj.put("xuhao", no1);
			}
			if (!("".equals(isValid) || isValid == null)) {
				obj.put("is_valid", Boolean.valueOf(isValid));
			}
			String sql = "db.collection(\"sys_msg\").doc(\""+id+"\").update({\"data\":"+obj.toString()+"})";
			HttpUtil.updateDatabase(sql);
		}
		
		updateData(tb);
		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "保存消息成功！！！");
		return object;
	}
	
	@ResponseBody
	@RequestMapping(value="/dbs/remove", method = RequestMethod.POST)
    public Object remove(String id, String tb, String fileId, HttpServletRequest request) {
		if (!("".equals(fileId) || fileId==null)) {
			HttpUtil.delFile(fileId);
		}
		
		String sql = "db.collection(\"sys_msg\").doc(\""+id+"\").remove()";
		HttpUtil.delDatabase(sql);
		
		updateData(tb);
		if (!"undefined".equals(fileId) && StringUtil.isNotNullOrEmpty(fileId) && fileId.lastIndexOf("/msg")>-1) {
			String path = Constant.UPLOAD_PATH;
			String filename = fileId.substring(fileId.lastIndexOf("/msg"));
			File file = new File(path+filename);
			if (file.exists()) {
				file.delete();
			}
		}
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "删除消息成功！！！");
		return object;
    }
	
	@ResponseBody
	@RequestMapping(value="/dbs/upload", method = RequestMethod.POST)
	public Object upload(@RequestParam("imgFile") MultipartFile file, HttpServletRequest req)
	        throws IllegalStateException, IOException {
		UserDetailModel udm = UserUtil.getUserDetail();
		String tb = req.getParameter("tb");
		String type = req.getParameter("type");
	    // 判断文件是否为空，空则返回失败页面
	    if (file.isEmpty()) {
	        return "failed";
	    }
	    // 获取文件存储路径（绝对路径）
	    String path = Constant.UPLOAD_PATH;
	    // 获取原文件名
	    String fileName = file.getOriginalFilename();
	    String tmppath = "msg/"+StringUtil.dateToString(new Date(), "yyyyMMdd")+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
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
		JSONArray array = new JSONArray();
		array.add(obj);
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
			System.out.println("updatesql = "+sql);
			HttpUtil.updateDatabase(sql);
		}
	}
	
	@RequestMapping(value="/dbs/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String path = Constant.UPLOAD_PATH;
		String id = request.getParameter("id");
    	String sql = "db.collection(\"sys_msg\").doc(\""+id+"\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			if (array != null && array.size()>0) {
				JSONObject jo = array.getJSONObject(0);
				if("jpg".equals(jo.getString("type")) || "mp3".equals(jo.getString("type"))){
					String shareUrl = jo.getString("src");
					String ext = shareUrl.substring(shareUrl.lastIndexOf("."));
					String filename = path+shareUrl.substring(shareUrl.lastIndexOf("/mp3_jpg")+8);
					
					File file = new File(filename);
					// 如果文件不存在 
					if (!file.exists()) { 
						request.setAttribute("message", "您要下载的资源已被删除！！"); 
					} 
					// 处理文件名 
				    String realname = UUID.randomUUID().toString().replace("-", "")+ext; 
				    // 设置响应头，控制浏览器下载该文件 
				    response.setHeader("content-disposition", "attachment;filename=" + realname); 
				    // 读取要下载的文件，保存到文件输入流 
				    FileInputStream in = null;
				    OutputStream out = null;
					try {
						in = new FileInputStream(filename);
						// 创建输出流 
					    out = response.getOutputStream(); 
					    // 创建缓冲区 
					    byte buffer[] = new byte[1024]; 
					    int len = 0; 
					    // 循环将输入流中的内容读取到缓冲区当中 
					    while ((len = in.read(buffer)) > 0) { 
					        // 输出缓冲区的内容到浏览器，实现文件下载 
					        out.write(buffer, 0, len); 
					    } 
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						// 关闭文件输入流 
					    try {
					    	if (in != null) {
					    		in.close();
					    	}
						} catch (IOException e) {
							e.printStackTrace();
						} 
					    // 关闭输出流 
					    try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						} 
					}
				}
			}
		}
	}
	
}
