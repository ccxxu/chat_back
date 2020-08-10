package club.mzwh.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author ccxxu
 *
 */
@Entity
@Table(name="mz_user_group")
public class UserGroupModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length = 32)
	private String id;
	
	/**
	 * 组名
	 */
	@Column(name = "user_id", length = 32)
	private String userId;

	/***
	 * 用户组状态   0：为启用   1：为禁用
	 */
	@Column(name = "group_id", length = 32)
	private String groupId;

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
