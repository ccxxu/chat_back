package club.mzwh.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.IBaseService;

public abstract class BaseServiceImpl<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements IBaseService<M, PK> {
	@Autowired
	protected IBaseDao<M, PK> baseDao;

	public abstract void setBaseDao(IBaseDao<M, PK> baseDao);

	@Override
	public PK save(M model) throws Exception {
		return baseDao.save(model);
	}

	@Override
	public void merge(M model) throws Exception {
		baseDao.merge(model);
	}

	@Override
	public void saveOrUpdate(M model) throws Exception {
		baseDao.saveOrUpdate(model);
	}

	@Override
	public void update(M model) throws Exception {
		baseDao.update(model);
	}

	@Override
	public void delete(PK id) throws Exception {
		baseDao.delete(id);
	}

	@Override
	public void deleteObject(M model) throws Exception {
		baseDao.deleteObject(model);
	}

	@Override
	public M get(PK id) throws Exception {
		return baseDao.get(id);
	}

	@Override
	public int countAll() throws Exception {
		return baseDao.countAll();
	}

	@Override
	public List<M> listAll() throws Exception {
		return baseDao.listAll();
	}

	@Override
	public List<M> listAll(int pageNumber, int pageSize) throws Exception {
		return baseDao.listAll(pageNumber, pageSize);
	}
	@Override
	public PageInfo<M> listAll(PageInfo<M> pageInfo) throws Exception{
		return baseDao.listAll(pageInfo);
	}
	@Override
	public void deleteAll() throws Exception{
		baseDao.deleteAll();
	}
	@Override
	public List<M> getListByAttr(String attribute,String name) throws Exception{
		return baseDao.getListByAttr(attribute,name);
	}
	@Override
	public List<M> getLikeByAttr(String attribute,String name) throws Exception{
		return baseDao.getLikeByAttr(attribute,name);
	}
	@Override
	public M getByAttr(String attribute,String name) throws Exception{
		return baseDao.getByAttr(attribute,name);
	}

	@Override
	public List<M> getListByAttr(String attribute, String name,String orderAttr) throws Exception{
		return baseDao.getListByAttr(attribute, name, orderAttr);
	}
	
	@Override
	public List<M> getListByAttr(String attribute, String name,String orderAttr,String orderName) throws Exception{
		return baseDao.getListByAttr(attribute, name, orderAttr, orderName);
	}
	
	@Override
	public List<M> getListByAttr(String[] attributes, Object[] values,String orderDesc) throws Exception{
		return baseDao.getListByAttr(attributes, values, orderDesc);
	}
	
	@Override
	public List<M> getListByAttr(String[] attributes, Object[] values, String[] orderAttr, String[] orderName) throws Exception{
		return baseDao.getListByAttr(attributes, values, orderAttr, orderName);
	}
	
	@Override
	public M getByAttr(String[] attributes, Object[] values,String orderDesc) throws Exception{
		return baseDao.getByAttr(attributes, values, orderDesc);
	}
	
	@Override
	public void setAttr(String attribute,Object value) throws Exception{
		baseDao.setAttr(attribute, value);
	}
	
	@Override
	public void deleteList(String attr,String value) throws Exception{
		baseDao.deleteList(attr, value);
	}
}
