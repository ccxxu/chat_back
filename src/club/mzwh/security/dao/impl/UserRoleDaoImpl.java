/**
 * 
 */
package club.mzwh.security.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import club.mzwh.common.dao.impl.BaseDaoImpl;
import club.mzwh.security.dao.UserRoleDao;
import club.mzwh.security.model.UserRoleModel;

/**
 * 
 * @author ccxxu
 * @version:2015-9-12 上午9:09:49  类说明
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRoleModel, String> implements UserRoleDao {

	public void deleteRoleListByUserId(String userId) {
		String sql = " delete from mz_user_role where user_id = :userId";
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter("userId", userId);
		query.executeUpdate();
	}

}
