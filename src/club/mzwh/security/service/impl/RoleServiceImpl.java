/**
 * 
 */
package club.mzwh.security.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.impl.BaseServiceImpl;
import club.mzwh.security.dao.RoleDao;
import club.mzwh.security.model.RoleModel;
import club.mzwh.security.service.RoleService;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:14:22 类说明
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<RoleModel, String>
		implements RoleService {
	private RoleDao roleDao;

	@Autowired
	@Qualifier("roleDao")
	@Override
	public void setBaseDao(IBaseDao<RoleModel, String> roleDao) {
		this.baseDao = roleDao;
		this.roleDao = (RoleDao) roleDao;

	}

	public PageInfo<RoleModel> getList(Map<String,String> searchMap, PageInfo<RoleModel> pageInfo) throws Exception {
		return this.roleDao.getList(searchMap, pageInfo);
	}
	
	public List<RoleModel> findListByUserId(String userId) {
		return this.roleDao.findListByUserId(userId);
	}

	public Integer getMaxOrderNum() {
		return this.roleDao.getMaxOrderNum();
	}
	
}
