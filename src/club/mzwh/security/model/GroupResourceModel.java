package club.mzwh.security.model;

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
@Table(name="mz_group_resource")
public class GroupResourceModel {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length = 32)
	private String id;
	
	/**
	 * 组名
	 */
	@Column(name = "group_id", length = 32)
	private String groupId;

	/***
	 * 用户组状态   0：为启用   1：为禁用
	 */
	@Column(name = "resource_id", length = 32)
	private String resourceId;

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getResourceId() {
		return resourceId;
	}
	
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
