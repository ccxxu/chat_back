package club.mzwh.article.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.article.dao.ArticleDao;
import club.mzwh.article.model.ArticleModel;
import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.util.StringUtil;

/**
 * @author: ccx
 * @Email: 
 * @version:2012-9-12 上午9:07:49
 *                    类说明
 */
@Repository("articleDao")
public class ArticleDaoImpl extends BaseDaoImpl<ArticleModel, String> implements ArticleDao {

	@SuppressWarnings("unchecked")
	public PageInfo<ArticleModel> getList(Map<String,String> searchMap, PageInfo<ArticleModel> pageInfo) throws Exception {
		String hql = " from ArticleModel rm ";
		String hqlCount = "select count(*) from ArticleModel rm ";
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
		String hql = "select max(rm.orderNum) from ArticleModel rm";
		Query query = this.getSession().createQuery(hql);
		Object obj = query.uniqueResult();
		if (StringUtil.isNotNullOrEmpty(obj)) {
			return Integer.valueOf(obj+"");
		} else {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ArticleModel> getList(Map<String,String> searchMap) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		String hql = " from ArticleModel rm ";
		String where = " where 1=1 ";
		String mainColKey = searchMap.get("mainColKey");
		if (StringUtil.isNotNullOrEmpty(mainColKey)) {
			where += " and rm.mainColKey = :mainColKey";
			params.put("mainColKey", mainColKey);
		}
		String orderBy = " order by pubDate desc";
		Query query = this.getSession().createQuery(hql+where+orderBy);
		for(String key:params.keySet()){
			query.setParameter(key, params.get(key));
		}
		query.setFirstResult(0);
		String pageSize = searchMap.get("pageSize");
		if (StringUtil.isNotNullOrEmpty(pageSize)) {
			query.setMaxResults(Integer.valueOf(pageSize));
		}
		return query.list();
	}
	
}
