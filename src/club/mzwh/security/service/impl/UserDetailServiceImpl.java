package club.mzwh.security.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import club.mzwh.common.util.StringUtil;
import club.mzwh.security.model.RoleModel;
import club.mzwh.security.service.RoleService;
import club.mzwh.user.model.GroupModel;
import club.mzwh.user.model.UserDetailModel;
import club.mzwh.user.model.UserModel;
import club.mzwh.user.service.GroupService;
import club.mzwh.user.service.UserService;

/**
 * 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-26 上午9:23:03 
 * 类说明
 */
@SuppressWarnings("deprecation")
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private RoleService roleService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel users = userService.findUserByName(username);
		if (users == null) {
			throw new AuthenticationServiceException("该用户不存在！");
		} else  if("1".equals(users.getStatus())){
			throw new AuthenticationServiceException("您的用户被禁用，请联系管理员！");
		}
		
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
		boolean enable = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserDetailModel user = new UserDetailModel(users.getName(),users.getUsername(), users.getPassword(), enable, accountNonExpired,
				credentialsNonExpired, accountNonLocked, grantedAuths);
		user.setPhotoPath(users.getPhotoPath());
		user.setUiStyle(users.getUiStyle());
		user.setMobile(users.getMobile());
		user.setCreateDate(StringUtil.dateToString(users.getCreateTime(), "yyyy-MM-dd"));
		user.setEmail(users.getEmail());
		user.setDepartment(users.getDepartment());
		user.setId(users.getId());
		user.setDuty(users.getUserComment());
		user.setPassword(users.getPassword());
		return user;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(UserModel user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<RoleModel> roleList = this.roleService.findListByUserId(user.getId());
		if (roleList != null && roleList.size()>0) {
			for (RoleModel role : roleList) {
				if (null != role.getName()) {
					authSet.add(new GrantedAuthorityImpl(role.getName()));
				}
			}
		}
		List<GroupModel> grouplist = this.groupService.findListByUserId(user.getId());
		if (grouplist != null && grouplist.size()>0) {
			for (GroupModel group : grouplist) {
				if (null != group.getName()) {
					authSet.add(new GrantedAuthorityImpl(group.getName()));
				}
			}
		}
		return authSet;
	}
}
