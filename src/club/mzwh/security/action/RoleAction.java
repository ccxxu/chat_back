package club.mzwh.security.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.UserUtil;
import club.mzwh.security.model.RoleModel;
import club.mzwh.security.model.UserRoleModel;
import club.mzwh.security.service.RoleService;
import club.mzwh.security.service.UserRoleService;

@Controller
public class RoleAction {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "/system/role/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/system/role/list", modelMap);
	}
	
	@RequestMapping(value = "/system/user/role/{userId}", method = RequestMethod.GET)
	public ModelAndView roleGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response, @PathVariable("userId") String userId) throws Exception {
		modelMap.put("userId", userId);
		List<RoleModel> list = this.roleService.findListByUserId(userId);
		StringBuffer sb = new StringBuffer("::");
		for (int i = 0; i < list.size(); i++) {
			RoleModel rm = list.get(i);
			sb.append(rm.getId()+"::");
		}
		modelMap.put("roles", sb.toString());
		return new ModelAndView("/system/user/role", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/system/role/list", method = RequestMethod.POST)
	public PageInfo<RoleModel> listPost(HttpServletRequest request, 
			HttpServletResponse response, PageInfo<RoleModel> pageInfo) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			pageInfo = this.roleService.getList(searchMap, pageInfo);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return pageInfo;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/system/user/role/{userId}", method = RequestMethod.POST)
	public PageInfo<RoleModel> rolePost(HttpServletRequest request, 
			HttpServletResponse response, PageInfo<RoleModel> pageInfo) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			pageInfo = this.roleService.getList(searchMap, pageInfo);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return pageInfo;
		}
	}
	
	@RequestMapping(value = "/system/role/add", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap) 
			throws Exception {
		return new ModelAndView("/system/role/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/system/role/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	RoleModel rm = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				rm = this.roleService.get(id);
			}
	    	if (rm == null) {
				rm = new RoleModel();
			}
	    	mmap.put("rm", rm);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (rm == null) {
				rm = new RoleModel();
			}
	    	mmap.put("rm", rm);
    	}
        return new ModelAndView("/system/role/edit", mmap);
    }
	
	@ResponseBody
	@RequestMapping(value = "/system/role/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String descc = request.getParameter("descc");
		String status = request.getParameter("status");
		String orderNum = request.getParameter("orderNum");
		RoleModel rm = null;
		try {
			if (StringUtil.isNotNullOrEmpty(id)) {
				rm = this.roleService.get(id);
			}
			if (rm == null) {
				rm = new RoleModel();
				rm.setCreateTime(new Date());
				rm.setCreator(UserUtil.getLoginName());
				rm.setName(name==null?"":name);
				rm.setDescc(descc==null?"":descc);
				if (StringUtil.isNotNullOrEmpty(orderNum)) {
					try {
						rm.setOrderNum(Integer.valueOf(orderNum));
					} catch (NumberFormatException e) {
						Integer max = this.roleService.getMaxOrderNum();
						rm.setOrderNum(max+1);
					}
				} else {
					Integer max = this.roleService.getMaxOrderNum();
					rm.setOrderNum(max+1);
				}
			} 
			rm.setUpdateTime(new Date());
			rm.setUpdator(UserUtil.getLoginName());
			if (StringUtil.isNotNullOrEmpty(name)) {
				rm.setName(name);
			}
			if (StringUtil.isNotNullOrEmpty(status)) {
				rm.setStatus(status);
			}
			if (StringUtil.isNotNullOrEmpty(descc)) {
				rm.setDescc(descc);
			}
			if (StringUtil.isNotNullOrEmpty(orderNum)) {
				try {
					rm.setOrderNum(Integer.valueOf(orderNum));
				} catch (NumberFormatException e) {
					Integer max = this.roleService.getMaxOrderNum();
					rm.setOrderNum(max+1);
				}
			}
			this.roleService.saveOrUpdate(rm);
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/system/role/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object remove(@PathVariable("id") String id, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		try {
			if (StringUtil.isNotNullOrEmpty(ids)) {
				String[] idAs = ids.split(",");
				for (String idA : idAs) {
					this.roleService.delete(idA);
				}
			} else if(StringUtil.isNotNullOrEmpty(id)) {
				this.roleService.delete(id);
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
	@RequestMapping(value="/user/role/save", method = RequestMethod.POST)
    public Object roleResourceSave(HttpServletRequest request) {
		String roleIds = request.getParameter("roleIds");
		String userId = request.getParameter("userId");
		try {
			if(StringUtil.isNotNullOrEmpty(roleIds)) {
				this.userRoleService.deleteRoleListByUserId(userId);
				String[] rolIds = roleIds.split(",");
				for (int i = 0; i < rolIds.length; i++) {
					UserRoleModel urm = new UserRoleModel();
					urm.setRoleId(rolIds[i]);
					urm.setUserId(userId);
					this.userRoleService.save(urm);
				}
			}
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
    }
	
}