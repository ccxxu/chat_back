package club.mzwh.core.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.TextEscapeUtils;

import club.mzwh.common.util.StringUtil;

import com.google.code.kaptcha.Constants;

/**
 * 带验证码校验功能的用户名、密码认证过滤器
 * 支持不输入验证码；支持验证码忽略大小写。
 * @author ccx
 * 
 */
public class ValidateCodeUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {

	private boolean postOnly = true;
	private boolean allowEmptyValidateCode = false;
	private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;
	private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	// session中保存的验证码
//	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "rand";
	public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = Constants.KAPTCHA_SESSION_KEY;
	// 输入的验证码
	public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "validateCode";
	/*
	@Autowired
    private SiteService siteService;
	
	@Autowired
	private ActionLogService actionService;
	
	@Autowired
	private LogCategoryService logCategoryService;
	*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: "+ request.getMethod());
		}
		
		/****
		 * 验证码
		 */
		checkValidateCode(request);
		System.out.println("ASDFGHJKKLL");
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if(username == null || "".equals(username) || password == null || "".equals(password)){
			throw new AuthenticationServiceException("用户名密码不能为空！");
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY,TextEscapeUtils.escapeEntities(username));
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		Authentication  authentication= this.getAuthenticationManager().authenticate(authRequest);
//		UserDetailModel user = (UserDetailModel)authentication.getPrincipal();
//		
//		String j_site = request.getParameter("j_site");
		/*
		SiteModel siteModel = new SiteModel();

		//其他站点的用户登录该站点
		if(j_site != null && !"".equals(j_site) && !j_site.equals(user.getWebSite())){
			try {
				
				if("administrator".equals(user.getUsername())){
					siteModel = siteService.getByAttr("nameId", j_site);
				}else {
					siteModel = siteService.getSubSiteByLogin(username, j_site);
				}

			} catch (Exception e) {
				throw new AuthenticationServiceException("未知错误！");
			}
			
		//不填站点默认为用户所属站点	
		}else {
			try {
				j_site = user.getWebSite();
				siteModel = siteService.getByAttr("nameId", j_site);
			} catch (Exception e) {
				throw new AuthenticationServiceException("未知错误！");
			}
		}
		
		if(siteModel == null){
			throw new AuthenticationServiceException("你访问的站点不存在或您没有访问权限！");
		}
		
		if(!"administrator".equals(user.getUsername())){
			//登录控制
			String serverName = request.getServerName();
			LoginUtil.checkServer(serverName, siteModel);
		}
		
		if("1".equals(siteModel.getSiteStatus())){
			throw new AuthenticationServiceException("该站点已经被停用！");
		}
		
		user.setCurrentSite(siteModel.getNameId());
		user.setCurrentSiteId(siteModel.getFdid());
		 */
		/*****
		 * 登录Log
		 */
		try {
			/*
			Date date = new Date();
			ActionLogModel logModel = new ActionLogModel();
			logModel.setUsername(user.getName());
			logModel.setUserID(user.getUsername());
			logModel.setWebsiteNameString(user.getCurrentSite());
			logModel.setUserIP(RequestUtil.getIP(request));
			logModel.setActionClass("登录管理");
			logModel.setActionMethod("登入");
			LogCategoryModel model = logCategoryService.getModelByClassAndMethod("login", "login");
			logModel.setLogCategoryModel(model);
			logModel.setCreateTime(date);
			actionService.save(logModel);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authentication;
	}

	/**
	 * 
	 * <li>比较session中的验证码和用户输入的验证码是否相等</li>
	 * 
	 */
	protected void checkValidateCode(HttpServletRequest request) {
		String sessionValidateCode = obtainSessionValidateCode(request);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtil.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		return request.getParameter(validateCodeParameter);
	}

	protected String obtainSessionValidateCode(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(sessionvalidateCodeField);
		return null == obj ? "" : obj.toString();
	}

	public boolean isPostOnly() {
		return postOnly;
	}

	@Override
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public String getValidateCodeName() {
		return sessionvalidateCodeField;
	}

	public void setValidateCodeName(String validateCodeName) {
		this.sessionvalidateCodeField = validateCodeName;
	}

	public boolean isAllowEmptyValidateCode() {
		return allowEmptyValidateCode;
	}

	public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
		this.allowEmptyValidateCode = allowEmptyValidateCode;
	}

}
