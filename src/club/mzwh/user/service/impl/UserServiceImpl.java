package club.mzwh.user.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.impl.BaseServiceImpl;
import club.mzwh.user.dao.UserDao;
import club.mzwh.user.model.UserModel;
import club.mzwh.user.service.UserService;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-7 上午9:01:52 类说明
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserModel, String> implements
		UserService {
	private UserDao userDao;

	@Autowired
	@Qualifier("userDao")
	@Override
	public void setBaseDao(IBaseDao<UserModel, String> userDao) {
		super.baseDao = userDao;
		this.userDao = (UserDao) userDao;
	}

	@Override
	public UserModel findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	public PageInfo<UserModel> getList(Map<String,String> searchMap, PageInfo<UserModel> pageInfo) {
		return this.userDao.getList(searchMap, pageInfo);
	}
	
}
