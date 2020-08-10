package club.mzwh.common.dao;

import java.util.List;

import club.mzwh.common.model.VisitModel;

public interface VisitDao extends IBaseDao<VisitModel, String> {
	
	List<VisitModel> getList() throws Exception;
	
}
