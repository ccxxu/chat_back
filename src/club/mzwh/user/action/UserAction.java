package club.mzwh.user.action;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.common.model.MsgModel;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.HttpUtil;
import club.mzwh.common.util.MD5Util;
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.UserUtil;
import club.mzwh.user.model.UserModel;
import club.mzwh.user.service.UserService;

@Controller
public class UserAction {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/system/user/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/system/user/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/system/user/list", method = RequestMethod.POST)
	public PageInfo<UserModel> listPost(HttpServletRequest request, 
			HttpServletResponse response, PageInfo<UserModel> pageInfo) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			pageInfo = this.userService.getList(searchMap, pageInfo);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return pageInfo;
		}
	}
	
	@RequestMapping(value = "/system/user/add", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap) 
			throws Exception {
		return new ModelAndView("/system/user/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/system/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	UserModel um = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				um = this.userService.get(id);
			}
	    	if (um == null) {
				um = new UserModel();
			}
	    	mmap.put("um", um);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (um == null) {
				um = new UserModel();
			}
	    	mmap.put("um", um);
    	}
        return new ModelAndView("/system/user/edit", mmap);
    }
	
    @RequestMapping(value = "/system/user/resetPwd", method = RequestMethod.GET)
	public ModelAndView resetPwdGet(ModelMap modelMap) throws Exception {
		return new ModelAndView("/system/user/resetPwd", modelMap);
	}
    
    @ResponseBody
    @RequestMapping(value = "/system/user/checkPassword", method = RequestMethod.GET)
    public boolean checkPassword(String password) {
    	String username = UserUtil.getLoginName();
        String intputPwd = MD5Util.doMD5(password, username);
        String sysPwd = UserUtil.getPassword();
        return sysPwd.equals(intputPwd);
    }
    
    @ResponseBody
    @RequestMapping(value = "/system/user/checkUsernameUnique", method = RequestMethod.GET)
    public boolean checkUsername(String username) {
    	String loginName = UserUtil.getLoginName();
    	if (!username.equals(loginName)) {
    		UserModel um = this.userService.findUserByName(username);
    		return um == null;
		}
        return true;
    }
    
	@ResponseBody
	@RequestMapping(value = "/system/user/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		String email = request.getParameter("email");
		String fax = request.getParameter("fax");
//		String orderNum = request.getParameter("orderNum");
		String tele = request.getParameter("tele");
		String uiStyle = request.getParameter("uiStyle");
		String position = request.getParameter("position");
		String userComment = request.getParameter("userComment");
		String linkId = request.getParameter("linkId");
		String photoPath = request.getParameter("photoPath");
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		UserModel um = null;
		try {
			if (StringUtil.isNotNullOrEmpty(id)) {
				um = this.userService.get(id);
			}
			if (um == null) {
				um = new UserModel();
				um.setCreateTime(new Date());
				um.setCreator(UserUtil.getLoginName());
				um.setName(name==null?"":name);
				um.setUsername(username==null?"":username);
				um.setEmail(email==null?"":email);
				um.setLinkId(linkId==null?"":linkId);
				um.setDepartment("");
				um.setFax(fax==null?"":fax);
				um.setTele(tele==null?"":tele);
				um.setUiStyle(uiStyle==null?"":uiStyle);
				um.setPosition(position==null?"":position);
				um.setUserComment(userComment==null?"":userComment);
				um.setPhotoPath(photoPath==null?"/mp3_jpg/head.jpg":photoPath);
				um.setMobile(mobile==null?"":mobile);
//				if (StringUtil.isNotNullOrEmpty(orderNum)) {
//					try {
//						rm.setOrderNum(Integer.valueOf(orderNum));
//					} catch (NumberFormatException e) {
//						Integer max = this.resourceService.getMaxOrderNum();
//						rm.setOrderNum(max+1);
//					}
//				} else {
//					Integer max = this.resourceService.getMaxOrderNum();
//					rm.setOrderNum(max+1);
//				}
			} 
			um.setUpdateTime(new Date());
			um.setUpdator(UserUtil.getLoginName());
			um.setStatus(status);
			um.setPwd("123456");
			um.setPassword(MD5Util.doMD5("123456", username));
			if (StringUtil.isNotNullOrEmpty(name)) {
				um.setName(name);
			}
			if (StringUtil.isNotNullOrEmpty(username)) {
				um.setUsername(username);
			}
			if (StringUtil.isNotNullOrEmpty(email)) {
				um.setEmail(email);
			}
			if (StringUtil.isNotNullOrEmpty(fax)) {
				um.setFax(fax);
			}
			if (StringUtil.isNotNullOrEmpty(tele)) {
				um.setTele(tele);
			}
			if (StringUtil.isNotNullOrEmpty(uiStyle)) {
				um.setUiStyle(uiStyle);
			}
			if (StringUtil.isNotNullOrEmpty(position)) {
				um.setPosition(position);
			}			
			if (StringUtil.isNotNullOrEmpty(userComment)) {
				um.setUserComment(userComment);
			}
			if (StringUtil.isNotNullOrEmpty(linkId)) {
				um.setLinkId(linkId);
			}
			if (StringUtil.isNotNullOrEmpty(photoPath)) {
				um.setPhotoPath(photoPath);
			}
			if (StringUtil.isNotNullOrEmpty(mobile)) {
				um.setMobile(mobile);
			}
			if (StringUtil.isNotNullOrEmpty(pwd)) {
				um.setPwd(pwd);
				um.setPassword(MD5Util.doMD5(pwd, username));
			}
			this.userService.saveOrUpdate(um);
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/system/user/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object remove(@PathVariable("id") String id, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		try {
			if (StringUtil.isNotNullOrEmpty(ids)) {
				String[] idAs = ids.split(",");
				for (String idA : idAs) {
					this.userService.delete(idA);
				}
			} else if(StringUtil.isNotNullOrEmpty(id)) {
				this.userService.delete(id);
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
	@RequestMapping(value = "/system/user/resetPwd", method = RequestMethod.POST)
	public Object resetPwdPost(ModelMap modelMap, HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String newPassword = request.getParameter("newPassword");
		if (StringUtil.isNotNullOrEmpty(id)) {
			UserModel um = this.userService.get(id);
			if (um != null) {
				um.setPwd(newPassword);
				um.setPassword(MD5Util.doMD5(newPassword, um.getUsername()));
				this.userService.saveOrUpdate(um);
				MsgModel mm = new MsgModel("0", "修改密码成功，重新登录后生效！！！");
				return mm;
			}
		}
		MsgModel mm = new MsgModel("1", "系统错误！！！");
		return mm;
	}
	
	@ResponseBody
	@RequestMapping(value = "/system/user/profile/save", method = RequestMethod.POST)
	public Object resetProfile(ModelMap modelMap, HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		try {
			if (StringUtil.isNotNullOrEmpty(id)) {
				UserModel um = this.userService.get(id);
				if (um != null) {
					if (StringUtil.isNotNullOrEmpty(name)) {
						um.setName(name);
					}
					if (StringUtil.isNotNullOrEmpty(title)) {
						um.setUserComment(title);
					}
					if (StringUtil.isNotNullOrEmpty(username)) {
						um.setUsername(username);
					}
					if (StringUtil.isNotNullOrEmpty(mobile)) {
						um.setMobile(mobile);
					}
					if (StringUtil.isNotNullOrEmpty(email)) {
						um.setEmail(email);
					}
					this.userService.saveOrUpdate(um);
					
					if ("i-love-life".equals(um.getLinkId())) {
						//小程序用户将用户数据同步到云服务数据库
						JSONObject obj = new JSONObject();
						obj.put("nickName", name);
						obj.put("title", title);
						String sql = "db.collection(\"sys_user\").doc(\"401\").update({\"data\":"+obj.toString()+"})";
						HttpUtil.updateDatabase(sql);
					}
					
				}
			}
			MsgModel mm = new MsgModel("0", "修改成功，重新登录后生效！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}

}
