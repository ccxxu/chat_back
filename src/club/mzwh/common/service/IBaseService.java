package club.mzwh.common.service;

import java.util.List;

import club.mzwh.common.pagination.PageInfo;

public interface IBaseService<M extends java.io.Serializable, PK extends java.io.Serializable> {

	public PK save(M model) throws Exception;

	public void saveOrUpdate(M model) throws Exception;

	public void update(M model) throws Exception;

	public void merge(M model) throws Exception;

	public void delete(PK id) throws Exception;

	public void deleteObject(M model) throws Exception;

	public M get(PK id) throws Exception;

	public int countAll() throws Exception;

	public List<M> listAll() throws Exception;

	public List<M> listAll(int pageNumber, int pageSize) throws Exception;
	
	public PageInfo<M> listAll(PageInfo<M> pageInfo) throws Exception;

	public void deleteAll() throws Exception;

	public List<M> getListByAttr(String attribute, String name) throws Exception;

	public List<M> getLikeByAttr(String attribute, String name) throws Exception;

	public M getByAttr(String attribute, String name) throws Exception;
	
	public M getByAttr(String[] attributes, Object[] values,String orderDesc) throws Exception;
	
	public List<M> getListByAttr(String attribute, String name,String orderAttr) throws Exception;
	
	public List<M> getListByAttr(String attribute, String name,String orderAttr,String orderName) throws Exception;
	
	public List<M> getListByAttr(String[] attributes, Object[] values,String orderDesc) throws Exception;
	
	public List<M> getListByAttr(String[] attributes, Object[] values, String[] orderAttr, String[] orderName) throws Exception;
	
	public void setAttr(String attribute,Object value) throws Exception;
	
	public void deleteList(String attr,String value) throws Exception;

}
