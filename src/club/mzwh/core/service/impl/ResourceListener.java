package club.mzwh.core.service.impl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-20 下午2:21:05
 *                    类说明： 初始化功能菜单 和对应的权限
 *                    初始化权限
 */
/**
 * 
 * @author ccxxu
 * 类说明： 初始化功能菜单 和对应的权限
 *        初始化权限
 */
public class ResourceListener implements ServletContextListener {
	Logger logger = Logger.getLogger(ResourceListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		try {
/*
			boolean flag = Boolean.parseBoolean(OperatePropertiesUtil.readValue("config.properties", "program.init"));
			//初始化站点模板default
			if(flag){
				
				ApplicationContext act = new ClassPathXmlApplicationContext("/spring-config*.xml");
				ResourceService resourceService = (ResourceService) act.getBean("resourceService");
				RoleService roleService = (RoleService) act.getBean("roleService");
				UserService userService = (UserService) act.getBean("userService");
				SiteService siteService = (SiteService) act.getBean("siteService");
				SiteTemplateService siteTemplateService = (SiteTemplateService) act.getBean("siteTemplateService");
				SiteCategoryService siteCategoryService = (SiteCategoryService) act.getBean("siteCategoryService");
				LogCategoryService logCategoryService = (LogCategoryService) act.getBean("logCategoryService");
				
				List<SiteTemplateModel> siteTemplateModels =siteTemplateService.getListByAttr("nameId", "default");
				if(siteTemplateModels.size() ==0){
					SiteTemplateModel siteTemplateModel = new SiteTemplateModel();
					siteTemplateModel.setName("default");
					siteTemplateModel.setNameId("default");
					siteTemplateService.save(siteTemplateModel);
				}
				
				
				String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
				File f = new File(path + "/spring-security-design.xml");
				SAXReader reader = new SAXReader();
				Document doc = reader.read(f);
				Element root = doc.getRootElement();
				Element foo;
				// 初始化权限
				for (Iterator i = root.elementIterator("role"); i.hasNext();) {
					foo = (Element) i.next();
					String name = foo.elementText("name");
					String descc = foo.elementText("descc");
					RoleModel roleModel = roleService.getByName(name);
					if (null == roleModel) {
						Date date = new Date();
						roleModel = new RoleModel();
						roleModel.setName(name);
						roleModel.setDescc(descc);
						roleModel.setCreateTime(date);
						roleModel.setUpdateTime(date);
						roleService.save(roleModel);
					}
				}
				// 初始化功能列表
				for (Iterator i = root.elementIterator("request"); i.hasNext();) {
					foo = (Element) i.next();
					Date date = new Date();
					String id = foo.attributeValue("id");
					String role = foo.elementText("role");
					String desc = foo.attributeValue("name");
					String pid = foo.elementText("pid");
					String hierarchical ="";
					ResourceModel resourceModel = resourceService.getById(id);
					ResourceModel parentModel = resourceService.getById(pid);
					if(null != parentModel){
						hierarchical =parentModel.getHierarchical();
					}
					if (null == resourceModel) {
						resourceModel = new ResourceModel();
						resourceModel.setName(foo.attributeValue("name"));
					}
					resourceModel.setId(id);
					resourceModel.setState(foo.attributeValue("state"));
					resourceModel.setHasSub(foo.attributeValue("hasSub"));
					resourceModel.setType(foo.elementText("type"));
					resourceModel.setShowMenu(foo.elementText("show"));
					resourceModel.setPid(pid);
					//resourceModel.set_parentId(Integer.parseInt(foo.elementText("pid")));
					resourceModel.setHierarchical(id+";"+hierarchical);
					resourceModel.setUrl(foo.elementText("url"));
					resourceModel.setUpdateTime(date);
					resourceModel.setCreateTime(date);
					String[] roles = role.split(";");
					Set<RoleModel> setRoleModels = new HashSet<RoleModel>();
					for (String str : roles) {
						RoleModel roleModel = roleService.getByName(str);
						if (null == roleModel) {
							roleModel = new RoleModel();
							roleModel.setName(str);
							roleModel.setDescc(desc);
							roleModel.setCreateTime(date);
							roleModel.setUpdateTime(date);
							roleService.save(roleModel);
						}
						setRoleModels.add(roleModel);
					}
					resourceModel.setRoles(setRoleModels);
					resourceService.saveOrUpdate(resourceModel);
				}
				//初始化系统站点类别
				Date date = new Date();
				List<SiteCategoryModel> listaaa = (List<SiteCategoryModel>) siteCategoryService.getListByAttr("nameEN", "webmaster");
				SiteCategoryModel siteCategoryModel = new SiteCategoryModel();
				if (listaaa.size() <1) {
					siteCategoryModel.setName("系统站点");
					//siteCategoryModel.setNameId("system");
					siteCategoryModel.setNameEN("webmaster");
					siteCategoryModel.setFullNameEN("/webmaster");
					siteCategoryModel.setState("open");
					siteCategoryModel.setParentId("100000001");
					siteCategoryModel.setCreateTime(date);
					siteCategoryModel.setUpdateTime(date);
					
				}else{
					siteCategoryModel =listaaa.get(0);
				}
				siteCategoryService.saveOrUpdate(siteCategoryModel);
				
				
				List<SiteCategoryModel> listRoot = (List<SiteCategoryModel>) siteCategoryService.getListByAttr("nameEN", "root");
				SiteCategoryModel rootsiteCategoryModel = new SiteCategoryModel();
				if (listRoot.size() <1) {
					rootsiteCategoryModel.setName("根站点");
					//siteCategoryModel.setNameId("system");
					rootsiteCategoryModel.setNameEN("root");
					rootsiteCategoryModel.setFullNameEN("/root");
					rootsiteCategoryModel.setState("open");
					rootsiteCategoryModel.setParentId("00");
					rootsiteCategoryModel.setCreateTime(date);
					rootsiteCategoryModel.setUpdateTime(date);
					
				}else{
					rootsiteCategoryModel =listRoot.get(0);
				}
				siteCategoryService.saveOrUpdate(rootsiteCategoryModel);
				// 初始化 初始站点
				
				List<SiteModel> list = (List<SiteModel>) siteService.getListByAttr("nameId", "webmaster");
				SiteModel systemModel = new SiteModel();
				if (list.size() <1) {
					systemModel.setName("系统站点");
					systemModel.setNameId("webmaster");
					//systemModel.setNameEN("system");
					//systemModel.setFullNameEN("/system");
					//systemModel.setState("open");
					//systemModel.setParentId("00");
					systemModel.setCreateTime(date);
					systemModel.setUpdateTime(date);
					systemModel.setSiteStatus("0");
					
				}else{
					systemModel =list.get(0);
				}
				//所有的资源
				List<ResourceModel> resourceModels =resourceService.listAll();
				systemModel.setResourceModels(new HashSet<ResourceModel>(resourceModels));
				systemModel.setSiteCategory(siteCategoryModel);
				siteService.saveOrUpdate(systemModel);
				
				//初始化 超级管理员
				List<UserModel> users = (List<UserModel>) userService.getListByAttr("username", "administrator");
				UserModel user = new UserModel();
				if(users.size() >0){
					user = users.get(0);
				}else{
					Md5PasswordEncoder md5 = new Md5PasswordEncoder();
					user.setUsername("administrator");
					user.setName("系统管理员");
					user.setUiStyle("default");
					user.setPassword(md5.encodePassword("123456", "administrator"));
					user.setSelf_purview("N");
					user.setStatus("0");
				}
	//			user.setSiteAdmin(systemModel);
				user.setSiteModel(systemModel);
	//			//所有角色
				List<RoleModel> roles = roleService.listAll();
				user.setRoles(new HashSet<RoleModel>(roles));
				userService.saveOrUpdate(user);
				
				File log_cagory = new File(path + "/log-category.xml");
				Document log_doc = reader.read(log_cagory);
				Element log_root = log_doc.getRootElement();
				// 初始化权限
				for (Iterator i = log_root.elementIterator("category"); i.hasNext();) {
					Element log_foo = (Element) i.next();
					String classAction = log_foo.elementText("classAction");
					String actionMethod = log_foo.elementText("actionMethod");
					String logName = log_foo.elementText("logName");
					LogCategoryModel model = logCategoryService.getModelByClassAndMethod(classAction, actionMethod);
					if(model == null){
						model = new LogCategoryModel();
						model.setActionClass(classAction);
						model.setActionMethod(actionMethod);
						model.setLogName(logName);
						logCategoryService.save(model);
					}
				}
				
				File pieceModel = new File(path + "/module-piece-init.xml");
				Document module_doc = reader.read(pieceModel);
				Element module_root = module_doc.getRootElement();
				ModuleService modelService = (ModuleService) act.getBean("moduleService");
				
				// 初始化权限
				for (Iterator i = module_root.elementIterator("module"); i.hasNext();) {
					Element module_foo = (Element) i.next();
					String name = module_foo.elementTextTrim("name");
					String content = module_foo.elementTextTrim("content");
					ModuleModel model = modelService.getByAttr("name", name);
					if(model == null){
						model = new ModuleModel();
						model.setName(name);
						model.setStatus("1");
						model.setPatternStatus("0");
						String mid = modelService.save(model);
						String modulePath = OperatePropertiesUtil.readValue("config.properties", "compnent.xls.path");
						ReadFileUtil.writeFile(modulePath, mid+".xsl", content);
					}
					
				}
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
