package club.mzwh.article.service;

import java.util.List;
import java.util.Map;

import club.mzwh.article.model.ArticleModel;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.IBaseService;

/**
 * @author: ccx
 * @Email: 
 * @version:2012-9-12 上午9:11:05
 *                    类说明
 */
public interface ArticleService extends IBaseService<ArticleModel, String> {
	
	PageInfo<ArticleModel> getList(Map<String,String> searchMap, PageInfo<ArticleModel> pageInfo) throws Exception;
	
	List<ArticleModel> getList(Map<String,String> searchMap) throws Exception;
	
	Integer getMaxOrderNum();
	
}
