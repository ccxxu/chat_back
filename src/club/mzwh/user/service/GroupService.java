package club.mzwh.user.service;

import java.util.List;
import java.util.Map;

import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.IBaseService;
import club.mzwh.user.model.GroupModel;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-7 上午9:00:35 类说明
 */

public interface GroupService extends IBaseService<GroupModel, String> {
	
	PageInfo<GroupModel> getList(Map<String,String> searchMap, PageInfo<GroupModel> pageInfo) throws Exception;
	
	List<GroupModel> findListByUserId(String userId);
	
	Integer getMaxOrderNum();
	
}
