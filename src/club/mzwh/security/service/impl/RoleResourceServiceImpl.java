/**
 * 
 */
package club.mzwh.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.service.impl.BaseServiceImpl;
import club.mzwh.security.dao.RoleResourceDao;
import club.mzwh.security.model.RoleResourceModel;
import club.mzwh.security.service.RoleResourceService;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:17:37 类说明
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl extends
		BaseServiceImpl<RoleResourceModel, String> implements RoleResourceService {
	private RoleResourceDao roleResourceDao;

	@Autowired
	@Qualifier("roleResourceDao")
	@Override
	public void setBaseDao(IBaseDao<RoleResourceModel, String> roleResourceDao) {
		this.baseDao = roleResourceDao;
		this.roleResourceDao = (RoleResourceDao) roleResourceDao;
	}
	
	public void deleteResourceListByRoleId(String roleId) {
		this.roleResourceDao.deleteResourceListByRoleId(roleId);
	}
	
}
