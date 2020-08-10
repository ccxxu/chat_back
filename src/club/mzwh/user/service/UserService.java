package club.mzwh.user.service;

import java.util.Map;

import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.IBaseService;
import club.mzwh.user.model.UserModel;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-7 上午9:00:35 类说明
 */

public interface UserService extends IBaseService<UserModel, String> {
	/**
	 * get userModel by username;
	 * 
	 * @param username
	 * @return
	 */
	UserModel findUserByName(String username);
	
	/**
	 * 
	 * @param searchMap
	 * @param pageInfo
	 * @return
	 */
	PageInfo<UserModel> getList(Map<String,String> searchMap, PageInfo<UserModel> pageInfo);
	
}
