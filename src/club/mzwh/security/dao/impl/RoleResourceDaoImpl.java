/**
 * 
 */
package club.mzwh.security.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.security.dao.RoleResourceDao;
import club.mzwh.security.model.RoleResourceModel;

/**
 * 
 * @author ccxxu
 * @version:2012-9-12 上午9:09:49
 *                    类说明
 */
@Repository("roleResourceDao")
public class RoleResourceDaoImpl extends BaseDaoImpl<RoleResourceModel, String> implements RoleResourceDao {

	public void deleteResourceListByRoleId(String roleId) {
		String sql = " delete from mz_role_resource where role_id = :roleId";
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter("roleId", roleId);
		query.executeUpdate();
	}

}
