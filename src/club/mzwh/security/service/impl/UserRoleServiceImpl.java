/**
 * 
 */
package club.mzwh.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.service.impl.BaseServiceImpl;
import club.mzwh.security.dao.UserRoleDao;
import club.mzwh.security.model.UserRoleModel;
import club.mzwh.security.service.UserRoleService;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:17:37 类说明
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends
		BaseServiceImpl<UserRoleModel, String> implements UserRoleService {
	
	private UserRoleDao userRoleDao;

	@Autowired
	@Qualifier("userRoleDao")
	@Override
	public void setBaseDao(IBaseDao<UserRoleModel, String> userRoleDao) {
		this.baseDao = userRoleDao;
		this.userRoleDao = (UserRoleDao) userRoleDao;
	}
	
	public void deleteRoleListByUserId(String userId) {
		this.userRoleDao.deleteRoleListByUserId(userId);
	}
	
}
