/**
 * 
 */
package club.mzwh.security.service;

import java.util.List;
import java.util.Map;

import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.IBaseService;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.model.RoleModel;
import club.mzwh.user.model.GroupModel;

/** 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:11:05 
 * 类说明 
 */
public interface ResourceService extends IBaseService<ResourceModel, String> {
	
	PageInfo<ResourceModel> getList(Map<String,String> searchMap, PageInfo<ResourceModel> pageInfo);
	
	List<ResourceModel> getList(Map<String,String> searchMap);

	void saveorupdate(ResourceModel rm);
	
	void updateKey(String oid, String nid);
	
	ResourceModel getModelById(String id);
	
	void updateData(String pid);
	
	String getMaxId(String pid);
	
	void delete(ResourceModel rm);
	
	Integer getMaxOrderNum();
	
	List<ResourceModel> findResourceListByUserId(String userId);
	
	List<ResourceModel> findResourceListByRoleId(String roleId);
	
	List<RoleModel> findRoleListByResourceId(String resourceId);
	
	List<GroupModel> findGroupListByResourceId(String resourceId);
	
}
