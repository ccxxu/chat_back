package club.mzwh.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import club.mzwh.common.util.UrlUtil;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.model.RoleModel;
import club.mzwh.security.service.ResourceService;
import club.mzwh.user.model.GroupModel;


//1 加载资源与权限的对应关系
/****
 * 获取资源的对应权限
 * @author BlueKing
 *
 */
public class SecurityMetadataSourceServiceImpl implements FilterInvocationSecurityMetadataSource {
	Logger logger = Logger.getLogger(SecurityMetadataSourceServiceImpl.class);
	@Autowired
	private ResourceService resourceService;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() throws Exception {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<ResourceModel> resources = this.resourceService.listAll();
			for (ResourceModel resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限名封装为Spring的security Object
				List<RoleModel> rolelist = this.resourceService.findRoleListByResourceId(resource.getId());
				if (rolelist != null && rolelist.size()>0) {
					for (RoleModel role : rolelist) {
						ConfigAttribute configAttribute = new SecurityConfig(role.getName());
						configAttributes.add(configAttribute);
					}
				}
				List<GroupModel> grouplist = this.resourceService.findGroupListByResourceId(resource.getId());
				if (grouplist != null && grouplist.size()>0) {
					for (GroupModel group : grouplist) {
						ConfigAttribute configAttribute = new SecurityConfig(group.getName());
						configAttributes.add(configAttribute);
					}
				}
				if(resourceMap.get(resource.getUrl()) == null){
					resourceMap.put(resource.getUrl(), configAttributes);
				}else {
					resourceMap.get(resource.getUrl()).addAll(configAttributes);
				}
			}
		}

		//Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
		//Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();

	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	
       HttpServletRequest request =((FilterInvocation) object).getHttpRequest();
		String requestUrl = UrlUtil.buildRequestUrl(request);
		if (resourceMap == null) {
			try {
				loadResourceDefine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resourceMap.get(requestUrl);
	}

}