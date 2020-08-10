package club.mzwh.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

/**
 * 用户类
 * @author ccxxu
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mz_user")
public class UserModel extends BaseMode {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length=32)
	private String id;
	
	/**
	 * 用户名
	 */
	@Column(length=100)
	private String name;
	
	/**
	 * 用户登陆名
	 */
	@Column(name = "username",length=100)
	private String username;
	
	/***
	 * 邮箱
	 */
	@Column(length=500)
	private String email;

	/****
	 * 固话
	 */
	@Column(length=20)
	private String tele;
	
	/***
	 * 手机
	 */
	@Column(length=20)
	private String mobile;
	
	/***
	 * 传真
	 */
	@Column(length=20)
	private String fax;
	
	/***
	 * 部门
	 */
	@Column(length=300)
	private String department;
	
	/***
	 * 职位
	 */
	@Column(length=300)
	private String position;
	
	/***
	 * 备注
	 */
	@Column(name = "user_comment", length=600)
	private String userComment;
	
	@Column(name = "photo_path", length=255)
	private String photoPath;
	
	@Column(name = "link_id", length=255)
	private String linkId;

	/**
	 * 皮肤样式
	 */
	@Column(name = "ui_style", length=30)
	private String uiStyle;
	
	//是否禁用    0：正常   1：禁用
	@Column(length=2)
	private String status;
	
	@Column(length=255)
	private String pwd;

	/**
	 * password
	 */
	@Column(length=200)
	private String password;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}
	
	public void setTele(String tele) {
		this.tele = tele;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	/**
	 * 设置
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * 获取
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取
	 * 
	 * @return the uiStyle
	 */
	public String getUiStyle() {
		return uiStyle;
	}

	/**
	 * 设置
	 * 
	 * @param uiStyle
	 *            the uiStyle to set
	 */
	public void setUiStyle(String uiStyle) {
		this.uiStyle = uiStyle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public String getLinkId() {
		return linkId;
	}
	
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

}
