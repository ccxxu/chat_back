package club.mzwh.core.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.common.service.VisitService;
import club.mzwh.common.util.TreeResource;
import club.mzwh.common.util.UserUtil;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.service.ResourceService;

@Controller
public class IndexAction {
	
	@Autowired
	private VisitService visitService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap) {
		try {
			String userId = UserUtil.getUserId();
			userId = userId==null?"":userId;
			List<ResourceModel> relist = this.resourceService.findResourceListByUserId(userId);
			TreeResource tr = new TreeResource(relist);
			List<ResourceModel> resultTree = tr.getTree();
//			for (int i = 0; i < resultTree.size(); i++) {
//				ResourceModel rm01 = resultTree.get(i);
//				System.out.println("=="+rm01.getName());
//				for (ResourceModel rm02 : rm01.getChildren()) {
//					System.out.println("===="+rm02.getName());
//					for (ResourceModel rm03 : rm02.getChildren()) {
//						System.out.println("======"+rm03.getName());
//					}
//				}
//			}
			modelMap.put("menus", resultTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/index", modelMap);
	}
	
	/**
	 * 切换到个人中心
	 * @Title: listPost
	 * @Description: TODO(描述)
	 * @param modelMap
	 * @return
	 * @throws Exception
	 * @author author
	 * @date 2020年3月14日
	 */
	@RequestMapping(value = "/system/profile")
	public ModelAndView listPost(ModelMap modelMap) throws Exception {
		return new ModelAndView("/system/profile", modelMap);
	}
	
	/**
	 * 切换主题
	 * @Title: switchSkin
	 * @Description: TODO(描述)
	 * @param mmap
	 * @return
	 * @author author
	 * @date 2020年3月14日
	 */
    @RequestMapping(value = "/system/switchSkin", method = RequestMethod.GET)
    public String switchSkin(ModelMap mmap) {
        return "/system/main/skin";
    }
    
    /**
     * 打开系统更新日志
     * @Title: syslog
     * @Description: TODO(描述)
     * @param mmap
     * @return
     * @author author
     * @date 2020年3月14日
     */
    @RequestMapping(value = "/system/main/log", method = RequestMethod.GET)
    public String syslog(ModelMap mmap) {
        return "/system/main/log";
    }
    
    /**
     * 进入系统首页
     * @Title: mainGet
     * @Description: TODO(描述)
     * @param modelMap
     * @return
     * @throws Exception
     * @author author
     * @date 2020年3月14日
     */
    @RequestMapping(value = "/system/main", method = RequestMethod.GET)
	public ModelAndView mainGet(ModelMap modelMap) throws Exception {
    	/*
    	List<Map<String, String>> visitlist = new ArrayList<Map<String,String>>();
    	Stack<Map<String, String>> stack = new Stack<Map<String,String>>();
    	List<VisitModel> list = this.visitService.getList();
    	for (int i = 0; i < list.size(); i++) {
    		Map<String, String> visitMap = new HashMap<String, String>();
    		VisitModel vm = list.get(i);
			visitMap.put("month", vm.getId().replace("-", "年")+"月");
			float sd = (float)(Math.round(vm.getCnt()*100))/10000;
			visitMap.put("cnt", sd+"");
			stack.push(visitMap);
		}
    	
    	int size = stack.size();
    	for (int i = 0; i < size; i++) {
    		visitlist.add(stack.pop());
		}
    	modelMap.put("visitlist", visitlist);
    	modelMap.put("sysinfo", new SystemModel());
    	*/
		return new ModelAndView("/main", modelMap);
	}
	
}
