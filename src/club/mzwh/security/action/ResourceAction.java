package club.mzwh.security.action;

import java.util.ArrayList;
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
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.UserUtil;
import club.mzwh.common.util.ZTreeMap;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.model.RoleResourceModel;
import club.mzwh.security.service.ResourceService;
import club.mzwh.security.service.RoleResourceService;

@Controller
public class ResourceAction {
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleResourceService roleResourceService;

	@RequestMapping(value = "/system/resource/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/system/resource/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/system/resource/list", method = RequestMethod.POST)
	public List<ResourceModel> listPost(HttpServletRequest request, 
			HttpServletResponse response) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			List<ResourceModel> list = this.resourceService.getList(searchMap);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<ResourceModel>();
		}
	}
	
	@RequestMapping(value = "/system/resource/add/{pid}", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap, @PathVariable("pid") String pid) 
			throws Exception {
		modelMap.put("pid", pid);
		return new ModelAndView("/system/resource/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/system/resource/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	ResourceModel rm = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				rm = this.resourceService.get(id);
			}
	    	if (rm == null) {
				rm = new ResourceModel();
			}
	    	mmap.put("rm", rm);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (rm == null) {
				rm = new ResourceModel();
			}
	    	mmap.put("rm", rm);
    	}
        return new ModelAndView("/system/resource/edit", mmap);
    }
    
    /**
     * 修改角色
     */
    @RequestMapping(value = "/system/resource/key/{id}", method = RequestMethod.GET)
    public ModelAndView key(@PathVariable("id") String id, ModelMap mmap) {
    	ResourceModel rm = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				rm = this.resourceService.get(id);
			}
	    	if (rm == null) {
				rm = new ResourceModel();
			}
	    	mmap.put("rm", rm);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (rm == null) {
				rm = new ResourceModel();
			}
	    	mmap.put("rm", rm);
    	}
        return new ModelAndView("/system/resource/key", mmap);
    }
	
    /**
     * 菜单选择树
     */
    @RequestMapping(value = "/system/resource/tree/{roleId}", method = RequestMethod.GET)
    public ModelAndView editResourceGet(@PathVariable("roleId") String roleId, ModelMap mmap) {
    	mmap.put("roleId", roleId);
        return new ModelAndView("/system/resource/tree", mmap);
    }
    
    /**
     * 菜单选择树
     */
    @ResponseBody
    @RequestMapping(value = "/system/resource/treeData", method = RequestMethod.GET)
    public List<Map<String, Object>> editResourcePost(String roleId) {
    	Map<String, String> searchMap = new HashMap<String, String>();
    	searchMap.put("visiable", "1");
    	List<ResourceModel> list = this.resourceService.getList(searchMap);
    	List<ResourceModel> selectlist = this.resourceService.findResourceListByRoleId(roleId);
    	List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>(); 
    	for (int i = 0; i < list.size(); i++) {
			ResourceModel rm = list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", rm.getId());
			map.put("checked", false);
			for (int j = 0; j < selectlist.size(); j++) {
				ResourceModel srm = selectlist.get(j);
				if (srm.getId().equals(rm.getId())) {
					map.put("checked", true);
					break;
				}
			}
			map.put("pid", rm.getPid());
			map.put("name", rm.getName());
			map.put("open", false);
			map.put("title", rm.getName());
			resultList.add(map);
		}
    	ZTreeMap ztm = new ZTreeMap(resultList);
        return ztm.getTree();
    }
    
	@ResponseBody
	@RequestMapping(value = "/system/resource/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String fdid = request.getParameter("fdid");
		String name = request.getParameter("name");
		String pid = request.getParameter("pid");
		String showMenu = request.getParameter("showMenu");
		String state = request.getParameter("state");
		String showType = request.getParameter("showType");
		String url = request.getParameter("url");
		String orderNum = request.getParameter("orderNum");
		String icon = request.getParameter("icon");
		String target = request.getParameter("target");
		String remark = request.getParameter("remark");
		String visible = request.getParameter("visible");
		String perms = request.getParameter("perms");
		ResourceModel rm = null;
		try {
			if (StringUtil.isNotNullOrEmpty(fdid)) {
				rm = this.resourceService.get(fdid);
			}
			if (rm == null) {
				rm = new ResourceModel();
				rm.setCreateTime(new Date());
				rm.setCreator(UserUtil.getLoginName());
				String max_id = this.resourceService.getMaxId(pid);
				if (StringUtil.isNotNullOrEmpty(max_id)) {
					int maxId = Integer.parseInt(max_id)+1;
					rm.setId(String.format("%0"+max_id.length()+"d", maxId));
				} else {
					rm.setId(pid+"01");
				}
				rm.setHasSub("0");
				rm.setVisible(visible==null?"":visible);
				rm.setTarget(target==null?"":target);
				rm.setPerms(perms==null?"":perms);
				rm.setIcon(icon==null?"":icon);
				rm.setRemark(remark==null?"":remark);
				if (StringUtil.isNotNullOrEmpty(orderNum)) {
					try {
						rm.setOrderNum(Integer.valueOf(orderNum));
					} catch (NumberFormatException e) {
						Integer max = this.resourceService.getMaxOrderNum();
						rm.setOrderNum(max+1);
					}
				} else {
					Integer max = this.resourceService.getMaxOrderNum();
					rm.setOrderNum(max+1);
				}
			} else {
				if (StringUtil.isNotNullOrEmpty(orderNum)) {
					try {
						rm.setOrderNum(Integer.valueOf(orderNum));
					} catch (NumberFormatException e) {
						Integer max = this.resourceService.getMaxOrderNum();
						rm.setOrderNum(max+1);
					}
				} 
			}
			rm.setUpdateTime(new Date());
			rm.setUpdator(UserUtil.getLoginName());
			if (StringUtil.isNotNullOrEmpty(pid)) {
				rm.setPid(pid);
				if ("0".equals(pid)) {
					rm.setHierarchical("0::"+rm.getId());
//					rm.setHasSub("0");
				} else {
					ResourceModel prm = this.resourceService.getModelById(pid);
					rm.setHierarchical(prm.getHierarchical()+"::"+rm.getId());
				}
			}
			rm.setState("1");
			rm.setShowMenu("1");
			if (StringUtil.isNotNullOrEmpty(name)) {
				rm.setName(name);
			}
			if (StringUtil.isNotNullOrEmpty(showMenu)) {
				rm.setShowMenu(showMenu);
			}
			if (StringUtil.isNotNullOrEmpty(state)) {
				rm.setState(state);
			}
			if (StringUtil.isNotNullOrEmpty(showType)) {
				rm.setType(showType);
			}
			if (StringUtil.isNotNullOrEmpty(url)) {
				rm.setUrl(url);
			}			
			if (StringUtil.isNotNullOrEmpty(visible)) {
				rm.setVisible(visible);
			}
			if (StringUtil.isNotNullOrEmpty(target)) {
				rm.setTarget(target);
			}
			if (StringUtil.isNotNullOrEmpty(perms)) {
				rm.setPerms(perms);
			}
			if (StringUtil.isNotNullOrEmpty(icon)) {
				rm.setIcon(icon);
			}
			if (StringUtil.isNotNullOrEmpty(remark)) {
				rm.setRemark(remark);
			}
			this.resourceService.saveorupdate(rm);
			this.resourceService.updateData(pid);
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/system/resource/keysave", method = RequestMethod.POST)
	public Object keysave(HttpServletRequest request, HttpServletResponse response) {
		String oid = request.getParameter("oid");
		String nid = request.getParameter("nid");
		try {
			if (StringUtil.isNotNullOrEmpty(oid) && StringUtil.isNotNullOrEmpty(nid)) {
				this.resourceService.updateKey(oid, nid);
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
	@RequestMapping(value="/system/resource/remove/{id}", method = RequestMethod.GET)
    public Object remove(@PathVariable("id") String id) {
		try {
			if(StringUtil.isNotNullOrEmpty(id)) {
				ResourceModel rm = this.resourceService.get(id);
				this.resourceService.delete(rm);
				this.resourceService.updateData(rm.getPid());
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
	@RequestMapping(value="/role/resource/save", method = RequestMethod.POST)
    public Object roleResourceSave(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		String resourceIds = request.getParameter("resourceIds");
		try {
			if(StringUtil.isNotNullOrEmpty(resourceIds)) {
				this.roleResourceService.deleteResourceListByRoleId(roleId);
				String[] resIds = resourceIds.split(",");
				for (int i = 0; i < resIds.length; i++) {
					RoleResourceModel rrm = new RoleResourceModel();
					rrm.setRoleId(roleId);
					rrm.setResourceId(resIds[i]);
					this.roleResourceService.save(rrm);
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
