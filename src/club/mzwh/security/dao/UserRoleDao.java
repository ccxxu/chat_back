/**
 * 
 */
package club.mzwh.security.dao;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.security.model.UserRoleModel;

/**
 * @author: ccx
 * @Email: xuxf203054@163.com
 * @version:2016-9-12 上午9:05:42 类说明
 */
public interface UserRoleDao extends IBaseDao<UserRoleModel, String> {
	
	void deleteRoleListByUserId(String userId);
	
}
