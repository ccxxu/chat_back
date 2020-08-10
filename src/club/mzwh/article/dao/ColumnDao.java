package club.mzwh.article.dao;

import java.util.List;
import java.util.Map;

import club.mzwh.article.model.ColumnModel;
import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;

/**
 * @author: ccx
 * @Email: 
 * @version:2012-9-12 上午9:05:42
 *                    类说明
 */
public interface ColumnDao extends IBaseDao<ColumnModel, String> {

	PageInfo<ColumnModel> getList(Map<String,String> searchMap, PageInfo<ColumnModel> pageInfo);
	
	List<ColumnModel> getList(Map<String,String> searchMap);
	
	void updateData(String pid);
	
	String getCount(String pid);
	
	String getMaxId(String pid);
	
	Integer getMaxOrderNum();
	
	ColumnModel getModelById(String id);
	
	List<ColumnModel> getModelByPid(String pid);
	
	List<ColumnModel> getModelByPid(String pid, String perpage);
	
	List<Map<String, Object>> getAllColumn();
	
	void updateKey(String oid, String nid);
	
}
