package club.mzwh.chat.dao.impl;

import org.springframework.stereotype.Repository;

import club.mzwh.chat.dao.DbDao;
import club.mzwh.chat.model.DbModel;
import club.mzwh.common.dao.impl.BaseDaoImpl;

/**  
 * @ClassName: DbDaoImpl
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年5月31日 
 */
@Repository("dbDao")
public class DbDaoImpl extends BaseDaoImpl<DbModel, String> implements DbDao {
	
	
}
