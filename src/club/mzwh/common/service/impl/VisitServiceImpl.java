package club.mzwh.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.dao.VisitDao;
import club.mzwh.common.model.VisitModel;
import club.mzwh.common.service.VisitService;

@Service("visitService")
public class VisitServiceImpl extends BaseServiceImpl<VisitModel, String> implements VisitService {

	private VisitDao visitDao;
	
	@Autowired
	@Qualifier("visitDao")
	@Override
	public void setBaseDao(IBaseDao<VisitModel, String> visitDao) {
		super.baseDao = visitDao;
		this.visitDao = (VisitDao)visitDao;
	}

	/**  
	 * @Title: getList
	 * @Description: TODO(描述)
	 * @return
	 * @throws Exception 
	 * @see club.mzwh.common.service.VisitService#getList() 
	 * @author author
	 * @date 2020年3月14日 
	 */ 
	public List<VisitModel> getList() throws Exception {
		return this.visitDao.getList();
	}
	
}
