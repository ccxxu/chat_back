package club.mzwh.core.filter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.common.util.JSONUtil;

public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception ex) {
	     if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // ajax 超时处理  
	            try{
	            	response.setContentType("application/json;charset=utf-8");
		    	 	PrintWriter out = response.getWriter();  
		            out.print(JSONUtil.msgJson("err", "异常提交：" + ex.getMessage()));  
		            out.flush();  
		            out.close();
	            }catch(Exception e){
	            	e.printStackTrace();
	            }
	            return null;
	        }else { // http 超时处理  
	        	Map<String, Object> model = new HashMap<String, Object>();  
	    	    model.put("ex", ex);
	    	    return new ModelAndView("error", model);
	        }  
	}
	
	public void print(){
		System.out.println("111111111111");
	}

}
