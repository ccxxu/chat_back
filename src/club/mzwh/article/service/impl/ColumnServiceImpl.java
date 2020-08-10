package club.mzwh.article.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.article.dao.ColumnDao;
import club.mzwh.article.model.ColumnModel;
import club.mzwh.article.service.ColumnService;
import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.impl.BaseServiceImpl;

/**
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-12 上午9:17:37 类说明
 */
@Service("columnService")
public class ColumnServiceImpl extends
		BaseServiceImpl<ColumnModel, String> implements ColumnService {
	private ColumnDao columnDao;

	@Autowired
	@Qualifier("columnDao")
	@Override
	public void setBaseDao(IBaseDao<ColumnModel, String> columnDao) {
		this.baseDao = columnDao;
		this.columnDao = (ColumnDao) columnDao;
	}

	public PageInfo<ColumnModel> getList(Map<String,String> searchMap, PageInfo<ColumnModel> pageInfo) {
		return this.columnDao.getList(searchMap, pageInfo);
	}
	
	public List<ColumnModel> getList(Map<String,String> searchMap) {
		return this.columnDao.getList(searchMap);
	}
	
	public ColumnModel getModelById(String id) {
		return this.columnDao.getModelById(id);
	}
	
	public List<ColumnModel> getModelByPid(String pid) {
		return this.columnDao.getModelByPid(pid);
	}
	
	public List<ColumnModel> getModelByPid(String pid, String perpage) {
		return this.columnDao.getModelByPid(pid, perpage);
	}
	
	public List<Map<String, Object>> getAllColumn() {
		return this.columnDao.getAllColumn();
	}
	
	public String getMaxId(String pid) {
		return this.columnDao.getMaxId(pid);
	}
	
	public Integer getMaxOrderNum() {
		return this.columnDao.getMaxOrderNum();
	}
	
	public void updateKey(String oid, String nid) {
		this.columnDao.updateKey(oid, nid);
	}

	public void saveorupdate(ColumnModel cm) {
		try {
			this.columnDao.saveOrUpdate(cm);
			this.columnDao.updateData(cm.getPid());
			System.out.println("after save: "+this.columnDao.getCount(cm.getPid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(ColumnModel cm) {
		try {
			this.columnDao.delete(cm.getId());
			this.columnDao.updateData(cm.getPid());
			System.out.println("after delete: "+this.columnDao.getCount(cm.getPid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateData(String pid) {
		this.columnDao.updateData(pid);
	}

}
