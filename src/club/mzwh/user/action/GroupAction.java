package club.mzwh.user.action;

import java.util.Date;
import java.util.HashMap;
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
import club.mzwh.user.model.GroupModel;
import club.mzwh.user.service.GroupService;

@Controller
public class GroupAction {
	
	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/system/group/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		return new ModelAndView("/system/group/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/system/group/list", method = RequestMethod.POST)
	public PageInfo<GroupModel> listPost(HttpServletRequest request, 
			HttpServletResponse response, PageInfo<GroupModel> pageInfo) {
		try {
			Map<String, String> searchMap = new HashMap<String, String>();
			pageInfo = this.groupService.getList(searchMap, pageInfo);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return pageInfo;
		}
	}
	
	@RequestMapping(value = "/system/group/add", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap) 
			throws Exception {
		return new ModelAndView("/system/group/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/system/group/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	GroupModel gm = null;
    	try {
	    	if (StringUtil.isNotNullOrEmpty(id)) {
				gm = this.groupService.get(id);
			}
	    	if (gm == null) {
				gm = new GroupModel();
			}
	    	mmap.put("gm", gm);
    	} catch (Exception e) {
    		e.printStackTrace();
    		if (gm == null) {
				gm = new GroupModel();
			}
	    	mmap.put("gm", gm);
    	}
        return new ModelAndView("/system/group/edit", mmap);
    }
	
	@ResponseBody
	@RequestMapping(value = "/system/group/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String descc = request.getParameter("descc");
		String status = request.getParameter("status");
		String orderNum = request.getParameter("orderNum");
		GroupModel gm = null;
		try {
			if (StringUtil.isNotNullOrEmpty(id)) {
				gm = this.groupService.get(id);
			}
			if (gm == null) {
				gm = new GroupModel();
				gm.setCreateTime(new Date());
				gm.setCreator(UserUtil.getLoginName());
				gm.setName(name==null?"":name);
				gm.setDescc(descc==null?"":descc);
				if (StringUtil.isNotNullOrEmpty(orderNum)) {
					try {
						gm.setOrderNum(Integer.valueOf(orderNum));
					} catch (NumberFormatException e) {
						Integer max = this.groupService.getMaxOrderNum();
						gm.setOrderNum(max+1);
					}
				} else {
					Integer max = this.groupService.getMaxOrderNum();
					gm.setOrderNum(max+1);
				}
			} 
			gm.setUpdateTime(new Date());
			gm.setUpdator(UserUtil.getLoginName());
			if (StringUtil.isNotNullOrEmpty(name)) {
				gm.setName(name);
			}
			if (StringUtil.isNotNullOrEmpty(status)) {
				gm.setStatus(status);
			}
			if (StringUtil.isNotNullOrEmpty(descc)) {
				gm.setDescc(descc);
			}
			if (StringUtil.isNotNullOrEmpty(orderNum)) {
				try {
					gm.setOrderNum(Integer.valueOf(orderNum));
				} catch (NumberFormatException e) {
					Integer max = this.groupService.getMaxOrderNum();
					gm.setOrderNum(max+1);
				}
			}
			this.groupService.saveOrUpdate(gm);
			MsgModel mm = new MsgModel("0", "操作成功！！！");
			return mm;
		} catch (Exception e) {
			e.printStackTrace();
			MsgModel mm = new MsgModel("1", "系统错误！！！");
			return mm;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/system/group/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Object remove(@PathVariable("id") String id, HttpServletRequest request) {
		String ids = request.getParameter("ids");
		try {
			if (StringUtil.isNotNullOrEmpty(ids)) {
				String[] idAs = ids.split(",");
				for (String idA : idAs) {
					this.groupService.delete(idA);
				}
			} else if(StringUtil.isNotNullOrEmpty(id)) {
				this.groupService.delete(id);
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
