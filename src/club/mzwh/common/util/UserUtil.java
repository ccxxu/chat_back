package club.mzwh.common.util;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import club.mzwh.user.model.UserDetailModel;

/**
 * 
 * @author: ccxxu
 * @Email: xuxf203054@163.com
 * @version:2012-9-21 上午9:34:24
 *                    类说明
 *                    获取session里 用户相关属性
 *                    username /webSite
 */
public class UserUtil {

	/**
	 * 获取用户名
	 * 
	 * @return
	 */
	public static String getName() {
		String returnString = "anonymous";
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (checkLogin(authentication)) {
			UserDetailModel details = (UserDetailModel) authentication.getPrincipal();
			returnString = details.getName();
		}

		return returnString;
	}

	/**
	 * 获取用户权限
	 */
	public static Collection<GrantedAuthority> getAuthorities() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		UserDetailModel details = (UserDetailModel) authentication.getPrincipal();
		return details.getAuthorities();
	}

	/**
	 * 获取登陆名
	 */
	/**
	 * 获取用户名
	 * 
	 * @return
	 */
	public static String getLoginName() {
		String returnString = "anonymous";
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (checkLogin(authentication)) {
			UserDetailModel details = (UserDetailModel) authentication.getPrincipal();
			returnString = details.getUsername();
		}
		return returnString;
	}

	/**
	 * 检查用户是否登录
	 */
	public static Boolean checkLogin(Authentication authentication) {
		Boolean isLogin = true;
		if (null == authentication || "anonymousUser".equals(authentication.getName())) {
			isLogin = false;
		}
		return isLogin;
	}

	/**
	 * 获取用户ip
	 */
	public static String getAddressIP() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		return details.getRemoteAddress();
	}
	
	public static String getPassword() {
		String returnString = "";
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (checkLogin(authentication)) {
			UserDetailModel details = (UserDetailModel) authentication.getPrincipal();
			returnString = details.getPassword();
		}
		return returnString;
	}
	
	/**
	 * 获取用户ID
	 * @Title: getUserId
	 * @Description: TODO(描述)
	 * @return
	 * @author author
	 * @date 2020年3月15日
	 */
	public static String getUserId() {
		String returnString = "anonymous";
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (checkLogin(authentication)) {
			UserDetailModel details = (UserDetailModel) authentication.getPrincipal();
			returnString = details.getId();
		}

		return returnString;
	}
	
	public static UserDetailModel getUserDetail() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (checkLogin(authentication)) {
			UserDetailModel details = (UserDetailModel) authentication.getPrincipal();
			return details;
		}
		return null;
	}
	
}
