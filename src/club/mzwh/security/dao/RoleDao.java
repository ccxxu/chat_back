/**
 * 
 */
package club.mzwh.security.dao;

import java.util.List;
import java.util.Map;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.security.model.RoleModel;

/** 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:05:42 
 * 类说明 
 */
public interface RoleDao extends IBaseDao<RoleModel, String> {

	PageInfo<RoleModel> getList(Map<String,String> searchMap, PageInfo<RoleModel> pageInfo) throws Exception;
	 
	List<RoleModel> findListByUserId(String userId);
	 
	Integer getMaxOrderNum();
	
}
