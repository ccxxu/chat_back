package club.mzwh.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.filter.DelegatingFilterProxy;

public class MyDelegatingFilterProxy extends DelegatingFilterProxy {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException{
		
//		Map<String,String[]> map = request.getParameterMap();
//		Set<Map.Entry<String, String[]>> keSet=map.entrySet();
//		for(Iterator<Map.Entry<String, String[]>> itr=keSet.iterator();itr.hasNext();){  
//		    Map.Entry<String, String[]> me=(Map.Entry<String, String[]>)itr.next(); 
//		    String key = me.getKey();
//		    if(!"content_html".equals(key) && !"content".equals(key) && !"htmlCode".equals(key)){
//		     	String[] ov=me.getValue();   
//		     	for(int i=0;i<ov.length;i++){
//		 	       	ov[i] = StringUtil.replaceXSS(ov[i]);
//		 	    }
//		    }
//		}
		super.doFilter(request, response, filterChain);
	}
}
