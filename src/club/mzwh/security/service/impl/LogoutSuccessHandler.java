package club.mzwh.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

//	@Autowired
//	private ActionLogService actionService;
//	
//	@Autowired
//	private LogCategoryService logCategoryService;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null) {
			/*****
			 * 退出登录Log
			 */
			try {
//				UserDetailModel user = (UserDetailModel)authentication.getPrincipal();
//				Date date = new Date();
//				ActionLogModel logModel = new ActionLogModel();
//				logModel.setUsername(user.getName());
//				logModel.setUserID(user.getUsername());
//				logModel.setWebsiteNameString(user.getCurrentSite());
//				logModel.setUserIP(RequestUtil.getIP(request));
//				logModel.setActionClass("登录管理");
//				logModel.setActionMethod("退出");
//				LogCategoryModel model = logCategoryService.getModelByClassAndMethod("loginOut", "loginOut");
//				logModel.setLogCategoryModel(model);
//				logModel.setCreateTime(date);
//				actionService.save(logModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
