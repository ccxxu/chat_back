package club.mzwh.security.service.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import club.mzwh.common.model.MsgModel;

public class SimpleUrlAuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		MsgModel mm = new MsgModel("1", exception.getLocalizedMessage());
		JSONObject result = JSONObject.fromObject(mm);
		System.out.println("failure=="+result.toString());
		response.getWriter().println(result.toString());	
	}

}
