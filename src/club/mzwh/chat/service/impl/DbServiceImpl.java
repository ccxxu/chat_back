package club.mzwh.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.article.dao.ArticleDao;
import club.mzwh.chat.dao.DbDao;
import club.mzwh.chat.model.DbModel;
import club.mzwh.chat.service.DbService;
import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.service.impl.BaseServiceImpl;

/**  
 * @ClassName: DbServiceImpl
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年5月31日 
 */
@Service("dbService")
public class DbServiceImpl extends BaseServiceImpl<DbModel, String> implements DbService {
	
	private DbDao dbDao;

	@Autowired
	@Qualifier("dbDao")
	@Override
	public void setBaseDao(IBaseDao<DbModel, String> baseDao) {
		this.baseDao = baseDao;
		this.dbDao = (DbDao) baseDao;
	}

}
