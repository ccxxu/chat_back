package club.mzwh.user.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserDetailModel extends User implements UserDetails {
	
	private String id;
	
	/**
	 * 用户名
	 */
	private String name;
	
	private String mobile;
	
	private String password;
	
	private String department;
	
	private String email;
	
	private String duty;
	
	private String createDate;
	
	/**
	 * 用户头像资源
	 */
	private String photoPath;
	
	/**
	 * 皮肤样式
	 */
	private String uiStyle;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 *获取
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 *设置
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhotoPath() {
		return this.photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDuty() {
		return duty;
	}
	
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *获取
	 * @return the uiStyle
	 */
	public String getUiStyle() {
		return uiStyle;
	}

	/**
	 *设置
	 * @param uiStyle the uiStyle to set
	 */
	public void setUiStyle(String uiStyle) {
		this.uiStyle = uiStyle;
	}
	
	public UserDetailModel(String name, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())  
            return false;
		return this.getUsername().equals(((UserDetailModel)obj).getUsername());
	}
	
	@Override
	public int hashCode() {
		 int result = 17;
	     result = 37 * result + (int) name.hashCode();
		 return result;
	}

}
