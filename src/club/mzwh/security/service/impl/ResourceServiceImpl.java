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
import club.mzwh.security.dao.ResourceDao;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.model.RoleModel;
import club.mzwh.security.service.ResourceService;
import club.mzwh.user.model.GroupModel;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:17:37 类说明
 */
@Service("resourceService")
public class ResourceServiceImpl extends
		BaseServiceImpl<ResourceModel, String> implements ResourceService {
	private ResourceDao resourceDao;

	@Autowired
	@Qualifier("resourceDao")
	@Override
	public void setBaseDao(IBaseDao<ResourceModel, String> resourceDao) {
		this.baseDao = resourceDao;
		this.resourceDao = (ResourceDao) resourceDao;
	}

	public PageInfo<ResourceModel> getList(Map<String,String> searchMap, PageInfo<ResourceModel> pageInfo) {
		return this.resourceDao.getList(searchMap, pageInfo);
	}
	
	public List<ResourceModel> getList(Map<String,String> searchMap) {
		return this.resourceDao.getList(searchMap);
	}
	
	public ResourceModel getModelById(String id) {
		return this.resourceDao.getModelById(id);
	}
	
	public String getMaxId(String pid) {
		return this.resourceDao.getMaxId(pid);
	}
	
	public Integer getMaxOrderNum() {
		return this.resourceDao.getMaxOrderNum();
	}
	
	public void updateKey(String oid, String nid) {
		this.resourceDao.updateKey(oid, nid);
	}

	public void saveorupdate(ResourceModel rm) {
		try {
			this.resourceDao.saveOrUpdate(rm);
//			this.resourceDao.updateData(rm.getPid());
			System.out.println("after save: "+this.resourceDao.getCount(rm.getPid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(ResourceModel rm) {
		try {
			this.resourceDao.delete(rm.getFdid());
//			this.resourceDao.updateData(rm.getPid());
			System.out.println("after delete: "+this.resourceDao.getCount(rm.getPid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(String pid) {
		this.resourceDao.updateData(pid);
	}
	
	public List<ResourceModel> findResourceListByUserId(String userId) {
		return this.resourceDao.findResourceListByUserId(userId);
	}
	
	public List<ResourceModel> findResourceListByRoleId(String roleId) {
		return this.resourceDao.findResourceListByRoleId(roleId);
	}
	
	public List<RoleModel> findRoleListByResourceId(String resourceId) {
		return this.resourceDao.findRoleListByResourceId(resourceId);
	}
	
	public List<GroupModel> findGroupListByResourceId(String resourceId) {
		return this.resourceDao.findGroupListByResourceId(resourceId);
	}

}
