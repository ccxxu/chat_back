/**
 * 
 */
package club.mzwh.security.dao;

import java.util.List;
import java.util.Map;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.model.RoleModel;
import club.mzwh.user.model.GroupModel;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:05:42
 *                    类说明
 */
public interface ResourceDao extends IBaseDao<ResourceModel, String> {

	PageInfo<ResourceModel> getList(Map<String,String> searchMap, PageInfo<ResourceModel> pageInfo);
	
	List<ResourceModel> getList(Map<String,String> searchMap);
	
	void updateData(String pid);
	
	String getCount(String pid);
	
	String getMaxId(String pid);
	
	Integer getMaxOrderNum();
	
	ResourceModel getModelById(String id);
	
	void updateKey(String oid, String nid);
	
	List<ResourceModel> findResourceListByUserId(String userId);
	
	List<ResourceModel> findResourceListByRoleId(String roleId);
	
	List<RoleModel> findRoleListByResourceId(String resourceId);
	
	List<GroupModel> findGroupListByResourceId(String resourceId);
	
}
