package club.mzwh.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.chat.dao.TbDao;
import club.mzwh.chat.model.TbModel;
import club.mzwh.chat.service.TbService;
import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.service.impl.BaseServiceImpl;

/**  
 * @ClassName: TbServiceImpl
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年5月31日 
 */
@Service("tbService")
public class TbServiceImpl extends BaseServiceImpl<TbModel, String> implements TbService {

	private TbDao tbDao;
	
	@Autowired
	@Qualifier("tbDao")
	@Override
	public void setBaseDao(IBaseDao<TbModel, String> baseDao) {
		this.baseDao = baseDao;
		this.tbDao = (TbDao) baseDao;
	}

}
