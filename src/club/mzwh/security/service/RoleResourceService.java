package club.mzwh.security.service;

import club.mzwh.common.service.IBaseService;
import club.mzwh.security.model.RoleResourceModel;

/** 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2015-9-12 上午9:11:05 
 * 类说明 
 */
public interface RoleResourceService extends IBaseService<RoleResourceModel, String> {
	
	void deleteResourceListByRoleId(String roleId);
	
}
