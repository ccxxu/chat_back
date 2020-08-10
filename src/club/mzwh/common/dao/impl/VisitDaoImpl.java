package club.mzwh.common.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.VisitDao;
import club.mzwh.common.model.VisitModel;

@Repository("visitDao")
public class VisitDaoImpl extends BaseDaoImpl<VisitModel, String>
		implements VisitDao {
	
	@SuppressWarnings("unchecked")
	public List<VisitModel> getList() throws Exception {
		String sql = " from VisitModel vm order by vm.id desc";
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult(0);
		query.setMaxResults(12);
		return query.list();
	}
	
}
