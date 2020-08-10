package club.mzwh.article.dao;

import java.util.List;
import java.util.Map;

import club.mzwh.article.model.ArticleModel;
import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;

/** 
 * @author: ccx
 * @Email: 
 * @version:2012-9-12 上午9:05:42 
 * 类说明 
 */
public interface ArticleDao extends IBaseDao<ArticleModel, String> {

	PageInfo<ArticleModel> getList(Map<String,String> searchMap, PageInfo<ArticleModel> pageInfo) throws Exception;
	
	List<ArticleModel> getList(Map<String,String> searchMap) throws Exception;
	 
	Integer getMaxOrderNum();
	
}
