package club.mzwh.user.dao;

import java.util.Map;
import java.util.Set;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.user.model.UserModel;

/**
 * 
 * @author ccxxu
 *
 */
public interface UserDao extends IBaseDao<UserModel, String> {
	Set<UserModel> getNoHasWebSiteUsers = null;

	/**
	 * get userModel by username
	 * 
	 * @param username
	 * @return
	 */
	UserModel findUserByName(String username);

	PageInfo<UserModel> getList(Map<String,String> searchMap, PageInfo<UserModel> pageInfo);
	
}
