/**
 * 
 */
package club.mzwh.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.StringUtil;
import club.mzwh.security.dao.ResourceDao;
import club.mzwh.security.model.ResourceModel;
import club.mzwh.security.model.RoleModel;
import club.mzwh.user.model.GroupModel;

/**
 * 
 * @author ccxxu
 * @version:2012-9-12 上午9:09:49
 *                    类说明
 */
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl<ResourceModel, String> implements ResourceDao {

	@SuppressWarnings("unchecked")
	public PageInfo<ResourceModel> getList(Map<String,String> searchMap, PageInfo<ResourceModel> pageInfo) {
		String hql = " from ResourceModel rm ";
		String hqlCount = "select count(*) from ResourceModel rm ";
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
	public List<ResourceModel> getList(Map<String,String> searchMap) {
		String hql = " from ResourceModel rm ";
		String where = " where 1=1 ";
		String orderBy = " order by rm.orderNum";
		Map<String,Object> params = new HashMap<String,Object>();
		String visible = searchMap.get("visible");
		if (StringUtil.isNotNullOrEmpty(visible)) {
			where += " and rm.visible = :visible";
			params.put("visible", visible);
		}
		String pid = searchMap.get("pid");
		if (StringUtil.isNotNullOrEmpty(pid)) {
			where += " and rm.pid = :pid";
			params.put("pid", pid);
		}
		Query query = this.getSession().createQuery(hql+where+orderBy);
		for(String key:params.keySet()){
			query.setParameter(key, params.get(key));
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public ResourceModel getModelById(String id) {
		String hql = " from ResourceModel rm where rm.id = :id";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id", id);
		List<ResourceModel> list = query.list();
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	public String getMaxId(String pid) {
		String hql = "select max(rm.id) from ResourceModel rm where rm.pid = :pid";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("pid", pid);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return obj.toString();
		}
		return null;
	}
	
	public String getCount(String pid) {
		String hql = "select count(*) from ResourceModel rm where rm.pid = :pid";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("pid", pid);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return obj.toString();
		}
		return null;
	}
	
	public Integer getMaxOrderNum() {
		String hql = "select max(rm.orderNum) from ResourceModel rm";
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
		String sql = "update mz_resource m1 set m1.has_sub = (select m3.cnt from (select count(*) cnt from mz_resource m2 where m2.pid = :pid) m3) where m1.id = :pid";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setParameter("pid", pid);
		System.out.println(sql.replaceAll(":pid", pid));
		query.executeUpdate();
	}
	
	public void updateKey(String oid, String nid) {
		String sql01 = "update mz_resource set id = :nid where id = :oid";
		SQLQuery query01 = this.getSession().createSQLQuery(sql01);
		query01.setParameter("nid", nid);
		query01.setParameter("oid", oid);
		System.out.println(sql01.replaceAll(":nid", nid).replaceAll(":oid", oid));
		query01.executeUpdate();
		String sql02 = "update mz_resource set pid = :nid where pid = :oid";
		SQLQuery query02 = this.getSession().createSQLQuery(sql02);
		query02.setParameter("nid", nid);
		query02.setParameter("oid", oid);
		System.out.println(sql02.replaceAll(":nid", nid).replaceAll(":oid", oid));
		query02.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<ResourceModel> findResourceListByUserId(String userId) {
		String sql = " from ResourceModel rm where rm.visible='1' and rm.id in (select rrm.resourceId from RoleResourceModel rrm, "
				+ "UserRoleModel urm where rrm.roleId=urm.roleId and urm.userId = :userId)";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("userId", userId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ResourceModel> findResourceListByRoleId(String roleId) {
		String sql = " from ResourceModel rm where rm.id in (select rrm.resourceId from RoleResourceModel rrm where rrm.roleId = :roleId)";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("roleId", roleId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleModel> findRoleListByResourceId(String resourceId) {
		String sql = " from RoleModel rm where rm.id in (select rrm.roleId from RoleResourceModel rrm where rrm.resourceId = :resourceId)";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("resourceId", resourceId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupModel> findGroupListByResourceId(String resourceId) {
		String sql = " from GroupModel gm where gm.id in (select grm.groupId from GroupResourceModel grm where grm.resourceId = :resourceId)";
		Query query = this.getSession().createQuery(sql);
		query.setParameter("resourceId", resourceId);
		return query.list();
	}

}
