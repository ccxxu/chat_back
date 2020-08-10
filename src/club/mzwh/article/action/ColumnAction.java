package club.mzwh.article.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.article.model.ArticleModel;
import club.mzwh.article.model.ColumnModel;
import club.mzwh.article.service.ArticleService;
import club.mzwh.article.service.ColumnService;
import club.mzwh.common.model.MsgModel;
import club.mzwh.common.util.Constant;
import club.mzwh.common.util.FileUtil;
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.TreeMap;
import club.mzwh.common.util.UserUtil;

@Controller
public class ColumnAction {
	
	@Autowired
	private ColumnService columnService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value = "/column/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/column/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/column/list", method = RequestMethod.POST)
	public List<ColumnModel> listPost(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			List<ColumnModel> list = this.columnService.getList(searchMap);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<ColumnModel>();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/column/colTreeData", method = {RequestMethod.GET, RequestMethod.POST})
	public List<Map<String, Object>> colTreeData(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			List<Map<String, Object>> list = this.columnService.getAllColumn();
			TreeMap treeMap = new TreeMap(list);
			return treeMap.getTree();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Map<String, Object>>();
		}
	}
	
	@RequestMapping(value = "/column/add/{pid}", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap, @PathVariable("pid") String pid) 
			throws Exception {
		modelMap.put("pid", pid);
		return new ModelAndView("/column/add", modelMap);
	}
	
	@RequestMapping(value = "/article/column/{id}", method = RequestMethod.GET)
	public ModelAndView arcol(ModelMap modelMap, @PathVariable("id") String id) 
			throws Exception {
		modelMap.put("pid", id);
		return new ModelAndView("/article/tree", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/column/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	ColumnModel rm = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				rm = this.columnService.get(id);
			}
	    	if (rm == null) {
				rm = new ColumnModel();
			}
	    	mmap.put("rm", rm);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (rm == null) {
				rm = new ColumnModel();
			}
	    	mmap.put("rm", rm);
    	}
        return new ModelAndView("/column/edit", mmap);
    }
    
    /**
     * 修改角色
     */
    @RequestMapping(value = "/column/key/{id}", method = RequestMethod.GET)
    public ModelAndView key(@PathVariable("id") String id, ModelMap mmap) {
    	ColumnModel cm = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				cm = this.columnService.get(id);
			}
	    	if (cm == null) {
	    		cm = new ColumnModel();
			}
	    	mmap.put("cm", cm);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (cm == null) {
    			cm = new ColumnModel();
			}
	    	mmap.put("cm", cm);
    	}
        return new ModelAndView("/column/key", mmap);
    }
	
	@ResponseBody
	@RequestMapping(value = "/column/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pid = request.getParameter("pid");
		String key = request.getParameter("key");
		String nameEN = request.getParameter("nameEN");
		String state = request.getParameter("state");
		String fullNameEN = request.getParameter("fullNameEN");
		String fullName = request.getParameter("fullName");
		String orderNum = request.getParameter("orderNum");
		String template = request.getParameter("template");
		String category = request.getParameter("category");
		String articleTemplate = request.getParameter("articleTemplate");
		String listTemplate = request.getParameter("listTemplate");
		String formatDateView = request.getParameter("formatDateView");
		String openType = request.getParameter("openType");
		String staticUrl = request.getParameter("staticUrl");
		String viewFront = request.getParameter("viewFront");
		String colKey = request.getParameter("colKey");
		String colValue = request.getParameter("colValue");
		String preCode = request.getParameter("preCode");
		String bodyCode = request.getParameter("bodyCode");
		String postCode = request.getParameter("postCode");
		String imageUrl_ = request.getParameter("imageUrl_");
		String ext = request.getParameter("ext");
		ColumnModel cm = null;
		try {
			if (StringUtil.isNotNullOrEmpty(id)) {
				cm = this.columnService.get(id);
			}
			if (cm == null) {
				cm = new ColumnModel();
				cm.setCreateTime(new Date());
				cm.setCreator(UserUtil.getLoginName());
				String max_id = this.columnService.getMaxId(pid);
				int maxId = Integer.parseInt(max_id)+1;
				cm.setKey(String.format("%0"+max_id.length()+"d", maxId));
				Integer max = this.columnService.getMaxOrderNum();
				cm.setOrderNum(max+1);
			} 
			cm.setUpdateTime(new Date());
			cm.setUpdator(UserUtil.getLoginName());
			if (StringUtil.isNotNullOrEmpty(pid)) {
				cm.setPid(pid);
				if ("0".equals(pid)) {
					cm.setHierarchical("0::"+cm.getKey());
				} else {
					ColumnModel prm = this.columnService.getModelById(pid);
					cm.setHierarchical(prm.getHierarchical()+"::"+cm.getKey());
				}
			}
			if (StringUtil.isNotNullOrEmpty(orderNum)) {
				try {
					cm.setOrderNum(Integer.valueOf(orderNum));
				} catch (NumberFormatException e) {
					
				}
			}
			if (StringUtil.isNotNullOrEmpty(name)) {
				cm.setName(name);
			}
			if (StringUtil.isNotNullOrEmpty(nameEN)) {
				cm.setNameEN(nameEN);
			}
			if (StringUtil.isNotNullOrEmpty(state)) {
				cm.setState(state);
			}
			if (StringUtil.isNotNullOrEmpty(fullNameEN)) {
				cm.setFullNameEN(fullNameEN);
			}
			if (StringUtil.isNotNullOrEmpty(fullName)) {
				cm.setFullName(fullName);
			}			
			if (StringUtil.isNotNullOrEmpty(template)) {
				cm.setTemplate(template);
			}
			if (StringUtil.isNotNullOrEmpty(articleTemplate)) {
				cm.setArticleTemplate(articleTemplate);
			}
			if (StringUtil.isNotNullOrEmpty(listTemplate)) {
				cm.setListTemplate(listTemplate);
			} else {
				
			}
			if (StringUtil.isNotNullOrEmpty(formatDateView)) {
				cm.setFormatDateView(formatDateView);
			}
			if (StringUtil.isNotNullOrEmpty(viewFront)) {
				cm.setViewFront(viewFront);
			}
			if (StringUtil.isNotNullOrEmpty(key)) {
				cm.setKey(key);
			}
			if (StringUtil.isNotNullOrEmpty(category)) {
				cm.setCategory(category);
			}
			if (StringUtil.isNotNullOrEmpty(staticUrl)) {
				cm.setStaticUrl(staticUrl);
			}
			if (StringUtil.isNotNullOrEmpty(openType)) {
				cm.setOpenType(openType);
			}
			if (StringUtil.isNotNullOrEmpty(colKey)) {
				cm.setColKey(colKey);
			}
			if (StringUtil.isNotNullOrEmpty(colValue)) {
				cm.setColValue(colValue);
			}
			if (StringUtil.isNotNullOrEmpty(preCode)) {
				cm.setPreCode(preCode);
			}
			if (StringUtil.isNotNullOrEmpty(bodyCode)) {
				cm.setBodyCode(bodyCode);
			}
			if (StringUtil.isNotNullOrEmpty(postCode)) {
				cm.setPostCode(postCode);
			}
			if ("1".equals(imageUrl_) && StringUtil.isNotNullOrEmpty(ext)) {
				String srcFile = Constant.ARTICLE_PATH+File.separator+"article"+File.separator+"col"+File.separator+"tmp_front_image"+ext;
				String name0 = System.currentTimeMillis()+"";
				String imageUrl = "/article/col/front_"+name0+ext;
				String desFile = Constant.ARTICLE_PATH+File.separator+"article"+File.separator+"col"+File.separator+"front_"+name0+ext;
				FileUtil.copy(srcFile, desFile);
				cm.setColumnImg(imageUrl);
			}
			this.columnService.saveorupdate(cm);
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/column/keysave", method = RequestMethod.POST)
	public Object keysave(HttpServletRequest request, HttpServletResponse response) {
		String oid = request.getParameter("oid");
		String nid = request.getParameter("nid");
		try {
			if (StringUtil.isNotNullOrEmpty(oid) && StringUtil.isNotNullOrEmpty(nid)) {
				this.columnService.updateKey(oid, nid);
			}
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/column/remove/{id}", method = RequestMethod.GET)
    public Object remove(@PathVariable("id") String id) {
		try {
			if(StringUtil.isNotNullOrEmpty(id)) {
				ColumnModel rm = this.columnService.get(id);
				this.columnService.delete(rm);
				this.columnService.updateData(rm.getPid());
			}
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
    }
	
	@ResponseBody
	@RequestMapping(value="/column/publish", method = RequestMethod.POST)
    public Object publish(String id) {
		try {
			if(StringUtil.isNotNullOrEmpty(id)) {
				ColumnModel cm = this.columnService.get(id);
				
				String filepath = Constant.ARTICLE_PATH+cm.getStaticUrl();
            	File file = new File(filepath);
            	if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
            	
            	PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8")));
            	if (StringUtil.isNotNullOrEmpty(cm.getPreCode())) {
					String preCode = cm.getPreCode();
					preCode = preCode.replaceAll("\\$columnImg", cm.getColumnImg());
					out.write(preCode);
				}
                
                if ("article".equals(cm.getCategory())) {
	                Map<String, String> searchMap = new HashMap<String, String>();
	                searchMap.put("mainColKey", cm.getKey());
					searchMap.put("pageSize", cm.getPerpage());
					List<ArticleModel> amlist = this.articleService.getList(searchMap);
	                if (amlist != null && amlist.size()>0) {
						for (int i = 0; i < amlist.size(); i++) {
							ArticleModel am = amlist.get(i);
							
							ColumnModel cm01 = this.columnService.getModelById(am.getMainColKey());
							String htmlContent = cm.getBodyCode();
							System.out.println("=-=-=-==="+htmlContent.indexOf("$imageUrl"));
							htmlContent = htmlContent.replaceAll("\\$amUrl", "/"+am.getPubDate().replaceAll("-", "").substring(0, 8)+"/"+am.getId()+".html");
							htmlContent = htmlContent.replaceAll("\\$imageUrl", am.getImageUrl());
							htmlContent = htmlContent.replaceAll("\\$colname", cm01.getName());
							htmlContent = htmlContent.replaceAll("\\$title", am.getTitle());
							htmlContent = htmlContent.replaceAll("\\$pubDate", am.getPubDate());
							htmlContent = htmlContent.replaceAll("\\$clickNum", "1532");
							String source = am.getSource();
							if (StringUtil.isNullOrEmpty(source)) {
								source = "";
							}
							htmlContent = htmlContent.replaceAll("\\$source", source);
							String digest = am.getDigest();
							if (digest != null && digest.length()>100) {
								digest = digest.substring(0,92)+"...";
							}
							htmlContent = htmlContent.replaceAll("\\$digest", digest);
							out.write(htmlContent);
						}
					}
                } else if ("column".equals(cm.getCategory())) {
					List<ColumnModel> list = this.columnService.getModelByPid(cm.getKey());
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							ColumnModel ccm = list.get(i);
							String htmlContent = cm.getBodyCode();
							htmlContent = htmlContent.replaceAll("\\$amUrl", ccm.getStaticUrl());
							htmlContent = htmlContent.replaceAll("\\$title", ccm.getName());
							out.write(htmlContent);
						}
					}
				}
                if (StringUtil.isNotNullOrEmpty(cm.getPostCode())) {
                	out.write(cm.getPostCode());
				}
        		out.flush();
        		out.close();
			}
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
    }
	
	@ResponseBody
	@RequestMapping(value="/column/image/upload", method = RequestMethod.POST)
	public Object upload(@RequestParam("imgFile") MultipartFile file, HttpServletRequest req)
	        throws IllegalStateException, IOException {
	    // 判断文件是否为空，空则返回失败页面
	    if (file.isEmpty()) {
	        return "failed";
	    }
	    // 获取文件存储路径（绝对路径）
	    String path = Constant.ARTICLE_PATH;
	    // 获取原文件名
	    String fileName = file.getOriginalFilename();
	    String ext = fileName.substring(fileName.lastIndexOf("."));
	    fileName = "/article/col/tmp_front_image"+fileName.substring(fileName.lastIndexOf("."));
	    // 创建文件实例
	    File filePath = new File(path, fileName);
	    // 如果文件目录不存在，创建目录
	    if (!filePath.getParentFile().exists()) {
	        filePath.getParentFile().mkdirs();
	        System.out.println("floder success: " + filePath);
	    }
	    // 第一步，上传的中间服务器
	    file.transferTo(filePath);
	    
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "图片上传成功!!!");
		object.put("src", Constant.WWW_HTTP+fileName);
		object.put("ext", ext);
		return object;
	}  
	
}
