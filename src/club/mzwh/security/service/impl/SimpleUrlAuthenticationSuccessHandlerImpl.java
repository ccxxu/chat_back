package club.mzwh.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import club.mzwh.common.model.MsgModel;

/**
 * 
 * @author ccxxu
 * @2020
 * 类说明 重写登陆成功后bean
 * 功能 扩展session
 * 
 */
public class SimpleUrlAuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		MsgModel mm = new MsgModel("0", "OK");
		JSONObject result = JSONObject.fromObject(mm);
		response.getWriter().println(result.toString());	
	}

}
