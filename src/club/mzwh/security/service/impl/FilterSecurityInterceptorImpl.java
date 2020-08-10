package club.mzwh.security.service.impl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class FilterSecurityInterceptorImpl extends AbstractSecurityInterceptor implements Filter {
	// 与applicationContext-security.xml里的myFilter的属性securityMetadataSource对应，
	// 其他的两个组件，已经在AbstractSecurityInterceptor定义
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
	private boolean observeOncePerRequest = true;

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void doFilter(ServletRequest arg1, ServletResponse arg2, FilterChain chain) throws IOException,
			ServletException {
		FilterInvocation fi = new FilterInvocation(arg1, arg2, chain);
		if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getName()) && !(noCheck(((FilterInvocation) fi).getRequestUrl()))) {
			HttpServletRequest request =(HttpServletRequest) arg1;
			HttpServletResponse response = (HttpServletResponse) arg2;
			response.sendRedirect(request.getContextPath()+"/view/login.jsp");
		} else {
			invoke(fi);
		}
	}

	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		if ((fi.getRequest() != null) && (fi.getRequest().getAttribute(FILTER_APPLIED) != null) && observeOncePerRequest) {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} else {

			if (fi.getRequest() != null) {
				fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
			}
			InterceptorStatusToken token = super.beforeInvocation(fi);
			try {
				fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			} finally {
				super.finallyInvocation(token);
			}
			super.afterInvocation(token, null);
		}
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		// 下面的MyAccessDecisionManager的supports方面必须放回true,否则会提醒类型错误
		return FilterInvocation.class;
	}
	private boolean noCheck(String url){
		String str="login.jsp||login";
		return str.contains(url) || url.contains("/image/rand");
	}
}