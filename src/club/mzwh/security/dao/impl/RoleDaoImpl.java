package club.mzwh.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.StringUtil;
import club.mzwh.security.dao.RoleDao;
import club.mzwh.security.model.RoleModel;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:07:49
 *                    类说明
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<RoleModel, String> implements RoleDao {

	@SuppressWarnings("unchecked")
	public PageInfo<RoleModel> getList(Map<String,String> searchMap, PageInfo<RoleModel> pageInfo) throws Exception {
		String hql = " from RoleModel rm ";
		String hqlCount = "select count(*) from RoleModel rm ";
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
	
	@SuppressWarnings("unchecked")
	public List<RoleModel> findListByUserId(String userId) {
		String sql = " from RoleModel rm where rm.id in (select urm.roleId from UserRoleModel urm where urm.userId = :userId)";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("userId", userId);
		return query.list();
	}

	public Integer getMaxOrderNum() {
		String hql = "select max(rm.orderNum) from RoleModel rm";
		Query query = this.getSession().createQuery(hql);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return Integer.valueOf(obj+"");
		} else {
			return 0;
		}
	}
}
