package club.mzwh.common.util;

import javax.servlet.http.HttpServletRequest;

import club.mzwh.user.model.UserModel;

/**
 * @模块：[该类所处的模块]
 * @描述: [SessionAttrUtil]请在此简要描述类的功能
 * @作者: zlw
 * @日期: 2015-12-16 下午1:19:57
 * <p>版权所有:(C)1998-2015 CIECC</p>
 */

public class LoginUtil {
	/** 登录页面地址*/
	public static final String login_url = "/imview/login";
	public static final String im_user = "im_user";
	public static final String im_user_name = "im_user_name";
	/** 随机令牌*/
	public static final String im_token = "im_token";
	
	/**
	* <p>方法名称: generateToken|描述: 生成令牌</p>
	* @param request
	* @return
	*/
	public static String generateToken(HttpServletRequest request){
		String rToken = StringUtil.getRandomString(32);
		request.getSession().setAttribute(im_token, rToken);
		return rToken;
	}
	/**
	* <p>方法名称: getCurrentToken|描述: 获取当前令牌</p>
	* @param request
	* @return
	*/
	public static String getCurrentToken(HttpServletRequest request){
		return StringUtil.nullToEmpty(request.getSession().getAttribute(im_token));
	}
	/**
	* <p>方法名称: doLogin|描述: 登录</p>
	* @param request
	* @param user
	*/
	public static void doLogin(HttpServletRequest request,UserModel user){
		generateToken(request);
		request.getSession().setAttribute(im_user, user);
		request.getSession().setAttribute(im_user_name, user.getUsername());
	}
	/**
	* <p>方法名称: doLogout|描述: 登出</p>
	* @param request
	*/
	public static void doLogout(HttpServletRequest request){
//		request.getSession().setAttribute(im_user_name, null);
//		request.getSession().setAttribute(im_user, null);
		request.getSession().removeAttribute(im_token);
		request.getSession().removeAttribute(im_user_name);
		request.getSession().removeAttribute(im_user);
		request.getSession().invalidate();
	}
	
	/**
	* <p>方法名称: getLoginUser|描述: 获取当前登录用户对象</p>
	* @param request
	* @return
	*/
	public static UserModel getLoginUser(HttpServletRequest request){
		return (UserModel) request.getSession().getAttribute(im_user);
	}
	/**
	* <p>方法名称: getLoginUserName|描述: 获取当前登录用户名</p>
	* @param request
	* @return
	*/
	public static String getLoginUserName(HttpServletRequest request){
		return StringUtil.nullToEmpty(request.getSession().getAttribute(im_user_name));
	}
	
	/**
	* <p>方法名称: isLogin|描述: 是否登录</p>
	* @param request
	* @return
	*/
	public static boolean isLogin(HttpServletRequest request){
		return !StringUtil.isNullOrEmpty(getLoginUserName(request));
	}
}
