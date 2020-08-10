package club.mzwh.user.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.user.dao.UserDao;
import club.mzwh.user.model.UserModel;

/**
 * @author: ccx
 * @Email: 
 * @version:2012-9-7 上午9:03:41 类说明
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<UserModel, String> implements UserDao {

	@Override
	public UserModel findUserByName(String username) {
		String hql = " from UserModel u where u.username=:username";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("username", username);
		return (UserModel)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo<UserModel> getList(Map<String,String> searchMap, PageInfo<UserModel> pageInfo) {
		String hql = " from UserModel um ";
		String hqlCount = "select count(*) from UserModel um ";
		String where = " where 1=1 ";
		Map<String,Object> params = new HashMap<String,Object>();
		Query query = this.getSession().createQuery(hql+where);
		Query queryCount = this.getSession().createQuery(hqlCount+where);
		for(String key:params.keySet()){
			queryCount.setParameter(key, params.get(key));
			query.setParameter(key, params.get(key));
		}
		Long count = (Long)queryCount.uniqueResult();
		pageInfo.setTotal(count.intValue());
		query.setFirstResult(pageInfo.getStart());
		query.setMaxResults(pageInfo.getPageSize());
		pageInfo.setRows(query.list());
		return pageInfo;
	}

}
