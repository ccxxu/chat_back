package club.mzwh.security.web.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@SuppressWarnings("deprecation")
public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		System.out.println("CALL Hsssssssssssssssssssssssssssss");
		response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        sb.append("{\"status\":\"error\",\"msg\":\"");
        
            sb.append("未登陆!");
        
        sb.append("\"}");
        out.write(sb.toString());
        out.flush();
        out.close();

	}


}
