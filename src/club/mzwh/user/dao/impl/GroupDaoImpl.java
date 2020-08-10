package club.mzwh.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.StringUtil;
import club.mzwh.user.dao.GroupDao;
import club.mzwh.user.model.GroupModel;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoImpl<GroupModel, String>
		implements GroupDao {
	
	@SuppressWarnings("unchecked")
	public List<GroupModel> findListByUserId(String userId) {
		String sql = " from GroupModel gm where gm.id in (select ugm.groupId from UserGroupModel ugm where ugm.userId = :userId)";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public PageInfo<GroupModel> getList(Map<String,String> searchMap, PageInfo<GroupModel> pageInfo) throws Exception {
		String hql = " from GroupModel gm ";
		String hqlCount = "select count(*) from GroupModel gm ";
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
	
	public Integer getMaxOrderNum() {
		String hql = "select max(gm.orderNum) from GroupModel gm";
		Query query = this.getSession().createQuery(hql);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return Integer.valueOf(obj+"");
		} else {
			return 0;
		}
	}
	
}
