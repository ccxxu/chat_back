package club.mzwh.article.service;

import java.util.List;
import java.util.Map;

import club.mzwh.article.model.ColumnModel;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.IBaseService;

/** 
 * @author: ccx
 * @Email: xx
 * @version:2012-9-12 上午9:11:05 
 * 类说明 
 */
public interface ColumnService extends IBaseService<ColumnModel, String> {
	
	PageInfo<ColumnModel> getList(Map<String,String> searchMap, PageInfo<ColumnModel> pageInfo);
	
	List<ColumnModel> getList(Map<String,String> searchMap);

	void saveorupdate(ColumnModel rm);
	
	void updateKey(String oid, String nid);
	
	ColumnModel getModelById(String id);
	
	List<ColumnModel> getModelByPid(String pid);
	
	List<ColumnModel> getModelByPid(String pid, String perpage);
	
	List<Map<String, Object>> getAllColumn();
	
	void updateData(String pid);
	
	String getMaxId(String pid);
	
	void delete(ColumnModel rm);
	
	Integer getMaxOrderNum();
	
}
