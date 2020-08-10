package club.mzwh.article.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
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
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.Constant;
import club.mzwh.common.util.FileUtil;
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.UserUtil;

@Controller
public class ArticleAction {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ColumnService ColumnService;

	@RequestMapping(value = "/article/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request, ModelMap modelMap) throws Exception {
		return new ModelAndView("/article/list", modelMap);
	}
	
	@ResponseBody
	@RequestMapping(value = "/article/list", method = RequestMethod.POST)
	public PageInfo<ArticleModel> listPost(HttpServletRequest request, 
			HttpServletResponse response, PageInfo<ArticleModel> pageInfo) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			pageInfo = this.articleService.getList(searchMap, pageInfo);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return pageInfo;
		}
	}
	
	@RequestMapping(value = "/article/add", method = RequestMethod.GET)
	public ModelAndView addGet(HttpServletRequest request, ModelMap modelMap) throws Exception {
		return new ModelAndView("/article/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/article/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	ArticleModel am = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
	    		am = this.articleService.get(id);
			}
	    	if (am == null) {
	    		am = new ArticleModel();
			} else {
				if (StringUtil.isNotNullOrEmpty(am.getMainColKey())) {
					ColumnModel cm1 = this.ColumnService.getModelById(am.getMainColKey());
					mmap.put("cm1", cm1);
				} else {
					mmap.put("cm1", new ColumnModel());
				}
				if (StringUtil.isNotNullOrEmpty(am.getSlaveColKey())) {
					ColumnModel cm2 = this.ColumnService.getModelById(am.getSlaveColKey());
					mmap.put("cm2", cm2);
				} else {
					mmap.put("cm2", new ColumnModel());
				}
				
				String fileName = Constant.ARTICLE_PATH + File.separator + am.getRedirectUrl();
				
				File file = new File(fileName);
		        InputStream in = null;
		        BufferedReader br = null;
		        StringBuffer sb = new StringBuffer();
		        if (file.isFile() && file.exists()) { //判断文件是否存在
		        	// 一次读多个字节
		        	char[] tempbytes = new char[1024];
		        	int byteread = 0;
		        	in = new FileInputStream(file);
		        	br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		        	// 读入多个字节到字节数组中，byteread为一次读入的字节数
		        	while ((byteread = br.read(tempbytes)) != -1) {
		        		//  System.out.write(tempbytes, 0, byteread);
		        		String str = new String(tempbytes, 0, byteread);
		        		sb.append(str);
		        	}
		        } 
		        String content = sb.toString();
		        content = content.replaceAll("\"", "\\\\\"");
		        System.out.println("==="+content.indexOf("\n"));
		        content = content.replaceAll("\n", "\\\\n");
		        mmap.put("uedtor01", content);
			}
	    	mmap.put("am", am);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (am == null) {
    			am = new ArticleModel();
			}
	    	mmap.put("am", am);
    	}
        return new ModelAndView("/article/edit", mmap);
    }
    
    
    @RequestMapping(value = "/article/recommond", method = RequestMethod.POST)
    public Object recommond(String id, ModelMap mmap) {
    	ArticleModel am = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
	    		am = this.articleService.get(id);
			}
	    	if (am == null) {
	    		am = new ArticleModel();
			} else {
				String filepath = Constant.ARTICLE_PATH+"/include/index/top.htm";
            	File file = new File(filepath);
            	if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8")));
				out.write("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n");
		        out.write("<article class=\"excerpt-minic excerpt-minic-index\">\n");
		        out.write("<h2><span class=\"red\">【今日推荐】</span><a href=\""+"/"+am.getPubDate().replaceAll("-", "").substring(0, 8)+"/"+am.getId()+".html"+"\" title=\""+am.getTitle()+"\">"+am.getTitle()+"</a></h2>\n");
		        String digest = am.getDigest();
		        if (StringUtil.isNotNullOrEmpty(digest)) {
		        	if (digest.length()>100) {
		        		digest = digest.substring(0, 97)+"...";
					}
				}
		        out.write("<p class=\"note\">"+digest+"</p>");
		        out.write("</article>");
		        out.flush();
        		out.close();
			}
	    	MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (am == null) {
    			am = new ArticleModel();
			}
	    	mmap.put("am", am);
    	}
    	MsgModel mm = new MsgModel("1", "系统错误！！！");
		return mm;
    }
    
	@ResponseBody
	@RequestMapping(value = "/article/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String subTitle = request.getParameter("subTitle");
		String shortTitle = request.getParameter("shortTitle");
		String digest = request.getParameter("digest");
		String pubDate01 = request.getParameter("pubDate01");
		String pubDate02 = request.getParameter("pubDate02");
		String source = request.getParameter("source");
		String mainColKey = request.getParameter("mainColKey");
		String slaveColKey = request.getParameter("slaveColKey");
		String htmlContent = request.getParameter("htmlContent");
		String imageUrl_ = request.getParameter("imageUrl_");
		String author = request.getParameter("author");
		String keyword = request.getParameter("keyword");
		String orderNum = request.getParameter("orderNum");
		String ext = request.getParameter("ext");
		ArticleModel am = null;
		try {
			String datepath = StringUtil.dateToString(new Date(), "yyyyMMdd");
			if (StringUtil.isNotNullOrEmpty(id)) {
				am = this.articleService.get(id);
			}
			if (am == null) {
				am = new ArticleModel();
				am.setCreateTime(new Date());
				am.setCreator(UserUtil.getLoginName());
				Integer max = this.articleService.getMaxOrderNum();
				am.setOrderNum(max+1);
			} 
			am.setUpdateTime(new Date());
			am.setUpdator(UserUtil.getLoginName());
			if (StringUtil.isNotNullOrEmpty(title)) {
				am.setTitle(title);
			}
			if (StringUtil.isNotNullOrEmpty(author)) {
				am.setAuthor(author);
			}
			if (StringUtil.isNotNullOrEmpty(keyword)) {
				am.setKeyword(keyword);
			}
			if (StringUtil.isNotNullOrEmpty(orderNum)) {
				try {
					am.setOrderNum(Integer.valueOf(orderNum));
				} catch (NumberFormatException e) {
					
				}
			}
			if (StringUtil.isNotNullOrEmpty(subTitle)) {
				am.setSubTitle(subTitle);
			}
			if (StringUtil.isNotNullOrEmpty(shortTitle)) {
				am.setShortTitle(shortTitle);
			}
			if (StringUtil.isNotNullOrEmpty(source)) {
				am.setSource(source);
			}
			if (StringUtil.isNotNullOrEmpty(digest)) {
				am.setDigest(digest);
			}
			if (StringUtil.isNotNullOrEmpty(mainColKey)) {
				am.setMainColKey(mainColKey);
			}
			if (StringUtil.isNotNullOrEmpty(slaveColKey)) {
				am.setSlaveColKey(slaveColKey);
			}
			if (StringUtil.isNotNullOrEmpty(pubDate01) && StringUtil.isNotNullOrEmpty(pubDate02)) {
				datepath = pubDate01.replaceAll("-", "");
				am.setPubDate(pubDate01 + " " + pubDate02);
			}
			if (StringUtil.isNotNullOrEmpty(htmlContent)) {
				String fileName = "article_"+System.currentTimeMillis()+".htm";
                try {
                	if (StringUtil.isNotNullOrEmpty(am.getRedirectUrl())) {
						String filepath = Constant.ARTICLE_PATH + File.separator + am.getRedirectUrl();
						File fileU = new File(filepath);
						if (fileU.isFile() && fileU.exists()) {
							fileU.delete();
						}
					}
                	String path = Constant.ARTICLE_PATH+File.separator+"article"+File.separator+"htm"+File.separator+datepath;
                	File file = new File(path, fileName);
                	if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();
					}
                	
                	
                	PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8")));
                    if (!htmlContent.startsWith("<meta charset=\"UTF-8\">")) {
                    	out.write("<meta charset=\"UTF-8\">");
					}
                    out.write(htmlContent);
            		out.flush();
            		out.close();
                    String redirectUrl = "/article/htm/"+datepath+"/"+fileName;
                    am.setRedirectUrl(redirectUrl);
                } catch (IOException e) {
                	e.printStackTrace();
                }
			}
			if ("1".equals(imageUrl_) && StringUtil.isNotNullOrEmpty(ext)) {
				String srcFile = Constant.ARTICLE_PATH+File.separator+"article"+File.separator+"img"+File.separator+"tmp_front_image"+ext;
				String name = System.currentTimeMillis()+"";
				String imageUrl = "/article/img/"+datepath+"/front_"+name+ext;
				String desFile = Constant.ARTICLE_PATH+File.separator+"article"+File.separator+"img"+File.separator+datepath+File.separator+"front_"+name+ext;
				FileUtil.copy(srcFile, desFile);
				am.setImageUrl(imageUrl);
			}
			this.articleService.saveOrUpdate(am);
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/article/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object remove(@PathVariable("id") String id, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		try {
			if (StringUtil.isNotNullOrEmpty(ids)) {
				String[] idAs = ids.split(",");
				for (String idA : idAs) {
					this.articleService.delete(idA);
				}
			} else if(StringUtil.isNotNullOrEmpty(id)) {
				this.articleService.delete(id);
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
	@RequestMapping(value="/article/image/upload", method = RequestMethod.POST)
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
	    fileName = "/article/img/tmp_front_image"+fileName.substring(fileName.lastIndexOf("."));
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
