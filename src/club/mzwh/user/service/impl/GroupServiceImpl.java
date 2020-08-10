package club.mzwh.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import club.mzwh.common.dao.IBaseDao;
import club.mzwh.common.pagination.PageInfo;
import club.mzwh.common.service.impl.BaseServiceImpl;
import club.mzwh.user.dao.GroupDao;
import club.mzwh.user.model.GroupModel;
import club.mzwh.user.service.GroupService;

@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<GroupModel, String> implements GroupService {

	private GroupDao groupDao;
	
	@Autowired
	@Qualifier("groupDao")
	@Override
	public void setBaseDao(IBaseDao<GroupModel, String> groupDao) {
		super.baseDao = groupDao;
		this.groupDao = (GroupDao)groupDao;
	}
	
	public PageInfo<GroupModel> getList(Map<String,String> searchMap, PageInfo<GroupModel> pageInfo) throws Exception {
		return this.groupDao.getList(searchMap, pageInfo);
	}
	
	
	public List<GroupModel> findListByUserId(String userId) {
		return this.groupDao.findListByUserId(userId);
	}
	
	public Integer getMaxOrderNum() {
		return this.groupDao.getMaxOrderNum();
	}
	
}
