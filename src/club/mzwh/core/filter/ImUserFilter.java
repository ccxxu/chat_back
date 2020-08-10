package club.mzwh.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import club.mzwh.common.util.LoginUtil;

/**
 * @模块：[前台用户公共模块]
 * @描述: [ImUserFilter]用户过滤器
 * @作者: zlw
 * @日期: 2015-12-15 下午2:44:55
 * <p>版权所有:(C)1998-2015 CIECC</p>
 */
public class ImUserFilter implements Filter {
	private String[] excludeUrl;
	@Override
	public void destroy() {
		
	}
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String URI = request.getRequestURI();
		String path = request.getContextPath();
		boolean isExclude = false;
		for (String url : excludeUrl) {
			if(URI.equals(path+url)){
				isExclude = true;
				break;
			}
		}
		if(isExclude){
			filterChain.doFilter(servletRequest, servletResponse);
		}else{
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			HttpSession session = request.getSession();
			if(null != session && LoginUtil.isLogin(request)){
				filterChain.doFilter(servletRequest, servletResponse);
			}else{
				response.sendRedirect(path + LoginUtil.login_url);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludeUrl = filterConfig.getInitParameter("excludeUrl").split(";");
	}
}
