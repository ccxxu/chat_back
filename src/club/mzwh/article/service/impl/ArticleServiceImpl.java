package club.mzwh.article.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.article.dao.ArticleDao;
import club.mzwh.article.model.ArticleModel;
import club.mzwh.article.service.ArticleService;
import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.impl.BaseServiceImpl;

/**
 * @author: ccx
 * @Email: 
 * @version:2012-9-12 上午9:14:22 类说明
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<ArticleModel, String>
		implements ArticleService {
	
	private ArticleDao articleDao;

	@Autowired
	@Qualifier("articleDao")
	@Override
	public void setBaseDao(IBaseDao<ArticleModel, String> articleDao) {
		this.baseDao = articleDao;
		this.articleDao = (ArticleDao) articleDao;

	}

	public PageInfo<ArticleModel> getList(Map<String,String> searchMap, PageInfo<ArticleModel> pageInfo) throws Exception {
		return this.articleDao.getList(searchMap, pageInfo);
	}
	
	public List<ArticleModel> getList(Map<String,String> searchMap) throws Exception {
		return this.articleDao.getList(searchMap);
	}

	public Integer getMaxOrderNum() {
		return this.articleDao.getMaxOrderNum();
	}
	
}
