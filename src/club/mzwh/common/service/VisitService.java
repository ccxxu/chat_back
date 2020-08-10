package club.mzwh.common.service;

import java.util.List;

import club.mzwh.common.model.VisitModel;

/**
 * 
 * @ClassName: VisitService
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年3月14日
 */
public interface VisitService extends IBaseService<VisitModel, String> {
	
	List<VisitModel> getList() throws Exception;
	
}
