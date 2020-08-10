package club.mzwh.user.dao;

import java.util.List;
import java.util.Map;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.user.model.GroupModel;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-7 上午9:02:49 类说明
 */

public interface GroupDao extends IBaseDao<GroupModel, String> {
	
	Integer getMaxOrderNum();
	
	public List<GroupModel> findListByUserId(String userId);
	
	PageInfo<GroupModel> getList(Map<String,String> searchMap, PageInfo<GroupModel> pageInfo) throws Exception;
	
}
