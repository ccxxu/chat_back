/**
 * 
 */
package club.mzwh.article.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import club.mzwh.article.dao.ColumnDao;
import club.mzwh.article.model.ColumnModel;
import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.StringUtil;

/**
 * 
 * @author ccxxu
 * @version:2012-9-12 上午9:09:49
 *                    类说明
 */
@Repository("columnDao")
public class ColumnDaoImpl extends BaseDaoImpl<ColumnModel, String> implements ColumnDao {

	@SuppressWarnings("unchecked")
	public PageInfo<ColumnModel> getList(Map<String,String> searchMap, PageInfo<ColumnModel> pageInfo) {
		String hql = " from ColumnModel rm ";
		String hqlCount = "select count(*) from ColumnModel rm ";
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
	public List<ColumnModel> getList(Map<String,String> searchMap) {
		String hql = " from ColumnModel rm ";
		String where = " where 1=1 ";
		String orderBy = " order by rm.orderNum";
		Map<String,Object> params = new HashMap<String,Object>();
		Query query = this.getSession().createQuery(hql+where+orderBy);
		for(String key:params.keySet()){
			query.setParameter(key, params.get(key));
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public ColumnModel getModelById(String id) {
		String hql = " from ColumnModel rm where rm.key = :id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id", id);
		List<ColumnModel> list = query.list();
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<ColumnModel> getModelByPid(String pid) {
		String hql = " from ColumnModel cm where cm.state='1' and cm.pid = :pid";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("pid", pid);
		List<ColumnModel> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ColumnModel> getModelByPid(String pid, String perpage) {
		String hql = " from ColumnModel cm where cm.state='1' and cm.pid = :pid limit 0, :pageSize ";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("pid", pid);
		query.setParameter("pageSize", Integer.valueOf(perpage));
		List<ColumnModel> list = query.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllColumn() {
		String hql = "select new map(cm.key as id, cm.pid as pid, cm.name as name, cm.name as title) from  ColumnModel cm where cm.state='1' order by orderNum";
		Query query = this.getSession().createQuery(hql);
		List<Map<String, Object>> list = query.list();
		return list;
	}
	
	public String getMaxId(String pid) {
		String hql = "select max(rm.key) from ColumnModel rm where rm.pid = :pid";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("pid", pid);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return obj.toString();
		}
		return pid+"00";
	}
	
	public String getCount(String pid) {
		String hql = "select count(*) from ColumnModel rm where rm.pid = :pid";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("pid", pid);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return obj.toString();
		}
		return null;
	}
	
	public Integer getMaxOrderNum() {
		String hql = "select max(rm.orderNum) from ColumnModel rm";
		Query query = this.getSession().createQuery(hql);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return Integer.valueOf(obj+"");
		} else {
			return 0;
		}
	}
	
	public void updateData(String pid) {
//		String sql = "update mz_resource m1 set m1.has_sub = (select m3.cnt from (select count(*) cnt from mz_resource m2 where m2.pid = :pid) m3)>0 where m1.id = :pid";
		String sql = "update mz_column m1 set m1.has_sub = (select m3.cnt from (select count(*) cnt from mz_column m2 where m2.pid = :pid) m3) where m1.id_key = :pid";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter("pid", pid);
		query.executeUpdate();
	}
	
	public void updateKey(String oid, String nid) {
		String sql01 = "update mz_column set id_key = :nid where id_key = :oid";
		SQLQuery query01 = this.getSession().createSQLQuery(sql01);
		query01.setParameter("nid", nid);
		query01.setParameter("oid", oid);
		query01.executeUpdate();
		String sql02 = "update mz_column set pid = :nid where pid = :oid";
		SQLQuery query02 = this.getSession().createSQLQuery(sql02);
		query02.setParameter("nid", nid);
		query02.setParameter("oid", oid);
		query02.executeUpdate();
	}

}
