package club.mzwh.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-7 上午11:11:21 类说明
 */
@Repository("baseDao")
public class BaseDaoImpl<M extends java.io.Serializable, PK extends java.io.Serializable> implements IBaseDao<M, PK> {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private Class<M> entityClass;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDaoImpl() {
		this.entityClass = null;
		Class<? extends BaseDaoImpl> c = getClass();
		Type t = c.getGenericSuperclass();

		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			entityClass = (Class<M>) p[0];
		}

	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public PK save(M model) throws Exception {
		return (PK) getSession().save(model);
	}

	@Override
	public void saveOrUpdate(M model) throws Exception {
		getSession().saveOrUpdate(model);
	}

	@Override
	public void update(M model) throws Exception {
		getSession().update(model);

	}

	@Override
	public void merge(M model) throws Exception {
		getSession().merge(model);
	}

	@Override
	public void delete(PK id) throws Exception {
		getSession().delete(this.get(id));

	}

	@Override
	public void deleteObject(M model) throws Exception {
		getSession().delete(model);

	}
	
	@Override
	public void deleteList(String attr,String value) throws Exception{
		List<M> list = this.getListByAttr(attr, value,null);
		for (M m : list) {
			this.deleteObject(m);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public M get(PK id) throws Exception {
		return (M) getSession().get(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> listAll() throws Exception {
		return getSession().createCriteria(entityClass).setCacheable(true).addOrder(Order.desc("createTime")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> listAll(int pageNumber, int pageSize) throws Exception {
		int start = (pageNumber - 1) * pageSize;
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.setFirstResult(start);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.desc("createTime"));
		return criteria.setCacheable(true).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<M> listAll(PageInfo<M> pageInfo) throws Exception {
		
//		Criteria criteria = getSession().createCriteria(entityClass);
//		criteria.setFirstResult(start);
//		criteria.setMaxResults(pageInfo.getPageSize());
//		criteria.addOrder(Order.desc("createTime"));
//		pageInfo.setRows(criteria.list());
		
		String hql = "select count(*) from "+ entityClass.getName();
		Long count =   (Long) getSession().createQuery(hql).iterate().next();
		pageInfo.setTotal(count.intValue());
		
		int start = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();
		String sql = " from "+entityClass.getName() + " as a order by a.createTime desc ";
		Query query =getSession().createQuery(sql);
		query.setFirstResult(start);
		query.setMaxResults(pageInfo.getPageSize());
		pageInfo.setRows(query.list());
		
		return pageInfo;
	}
	
	@Override
	public int countAll() throws Exception {
		String hql = "select count(fdid) from "+entityClass.getName();
		Long count =   (Long) getSession().createQuery(hql).list().get(0);
		return count.intValue();
	}

	@Override
	public List<M> pre(PK pk, int pageNumber, int pageSize) throws Exception {
		return null;
	}

	@Override
	public List<M> next(PK pk, int pageNumber, int pageSize) throws Exception {
		return null;
	}

	@Override
	public void deleteAll() throws Exception {
		List<M> list = this.listAll();
		for (M m : list) {
			this.deleteObject(m);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> getListByAttr(String attribute, String name) throws Exception {

		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(attribute, name));
		criteria.addOrder(Order.desc("createTime"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<M> getListByAttr(String attribute, String name,String orderAttr) throws Exception {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(attribute, name));
		if(orderAttr != null && !"".equals(orderAttr)){
			criteria.addOrder(Order.desc(orderAttr));
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<M> getListByAttr(String attribute, String name,String orderAttr,String orderName) throws Exception {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(attribute, name));
		if(orderAttr != null && !"".equals(orderAttr)){
			if("asc".equals(orderName)){
				criteria.addOrder(Order.asc(orderAttr));
			}else{
				criteria.addOrder(Order.desc(orderAttr));
			}
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<M> getLikeByAttr(String attribute, String name) {
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.like(attribute, name, MatchMode.ANYWHERE));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public M getByAttr(String attribute, String name) throws Exception {
		M m = null;
		Criteria criteria = getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(attribute, name));
		criteria.addOrder(Order.desc("createTime"));
		List<M> list = criteria.list();
		if (list.size() > 0) {
			m = list.get(0);
		}
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<M> getListByAttr(String[] attributes, Object[] values,String orderDesc) throws Exception {
		Criteria criteria = getSession().createCriteria(entityClass);
		for(int i=0;i<attributes.length;i++){
			criteria.add(Restrictions.eq(attributes[i], values[i]));
		}
		if(orderDesc != null){
			criteria.addOrder(Order.desc(orderDesc));
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<M> getListByAttr(String[] attributes, Object[] values,String[] orderAttr,String[] orderName) throws Exception {
		Criteria criteria = getSession().createCriteria(entityClass);
		for(int i=0;i<attributes.length;i++){
			criteria.add(Restrictions.eq(attributes[i], values[i]));
		}
		if(orderAttr != null){
			for (int i=0;i<orderAttr.length;i++) {
				if("asc".equals(orderName[i])){
					criteria.addOrder(Order.asc(orderAttr[i]));
				}else{
					criteria.addOrder(Order.desc(orderAttr[i]));
				}
			}
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public M getByAttr(String[] attributes, Object[] values,String orderDesc) throws Exception {
		M m = null;
		Criteria criteria = getSession().createCriteria(entityClass);
		for(int i=0;i<attributes.length;i++){
			criteria.add(Restrictions.eq(attributes[i], values[i]));
		}
		if(orderDesc != null){
			criteria.addOrder(Order.desc(orderDesc));
		}
		List<M> list = criteria.list();
		if (list.size() > 0) {
			m = list.get(0);
		}
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<Map<String,String>> list(String fields,String hql,String order,Map<String,Object> params,PageInfo<Map<String,String>> pageInfo) throws Exception{
		Query countQuery = this.getSession().createQuery("select count(*) from  " + hql );
		Query query = this.getSession().createQuery("select " + fields + " from " + hql + order);
		if(params != null){
			for(String key:params.keySet()){
				if(key.startsWith("sqlIn_")){
					countQuery.setParameterList(key, (Object[]) params.get(key));
					query.setParameterList(key,(Object[]) params.get(key));
				}else {
					countQuery.setParameter(key, params.get(key));
					query.setParameter(key, params.get(key));
				}
			}
		}
		Long totol = (Long)countQuery.uniqueResult();
		pageInfo.setTotal(totol.intValue());
		int start = (pageInfo.getPageNum()-1)*pageInfo.getPageSize();
		query.setFirstResult(start);
		query.setMaxResults(pageInfo.getPageSize());
		pageInfo.setRows(query.list());
		return pageInfo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Map<String,String>> list_new(String fields,String hql,String order,Map<String,Object> params,PageInfo<Map<String,String>> pageInfo) throws Exception{
		Query countQuery = this.getSession().createQuery("select count(*) from  " + hql );
		Query query = this.getSession().createQuery("select " + fields + " from " + hql + order);
		if(params != null){
			for(String key:params.keySet()){
				Object value = params.get(key);
				if(value instanceof Collection){
					query.setParameterList(key, (Collection)value);
				}else if(value instanceof Object[]){
					query.setParameterList(key, (Object[])value);
				}else {
					query.setParameter(key, value);
				}
			}
		}
		Long totol = (Long)countQuery.uniqueResult();
		pageInfo.setTotal(totol.intValue());
		int start = (pageInfo.getPageNum()-1)*pageInfo.getPageSize();
		query.setFirstResult(start);
		query.setMaxResults(pageInfo.getPageSize());
		pageInfo.setRows(query.list());
		return pageInfo;
	}
	
	@Override
	public void setAttr(String attribute,Object value) throws Exception{
		String hql = " update " + entityClass + " as a set a." + attribute + "=:value ";
		Query query =getSession().createQuery(hql);
		query.setParameter("value", value);
		query.executeUpdate();
	}
	
	/*@Override
	public String getSeqId(String seq_name){
		String sql = "select " + seq_name + ".NEXTVAL from dual";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query.uniqueResult().toString();
	}*/
}
