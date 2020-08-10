package club.mzwh.chat.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.common.util.HttpUtil;

import com.google.code.kaptcha.Constants;


/**
 * 
 * @author ccxxu
 *
 */
@Controller
public class LoginAction {
	
	Logger log = Logger.getLogger(LoginAction.class);
	
//	appID
//	wx4ff64945425567ec 
//	appsecret
//	d4757b6ee56885e79c976998109b5ca3 
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(ModelMap modelMap)
			throws Exception {
		return new ModelAndView("/login", modelMap);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object loginPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject object = new JSONObject();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String validateCode = request.getParameter("validateCode");
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		if (!"401".equals(username) || !"401".equals(password)) {
			object.put("code", -1);
			object.put("msg", "用户名或密码不正确!!!");
			return object;
		}
		String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!code.equals(validateCode)) {
			object.put("code", -1);
			object.put("msg", "验证码不正确!!!");
			return object;
		}
		sessionMap.put("username", username);
		String sql = "db.collection(\"sys_user\").doc(\"401\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		sessionMap.put("model", new JSONObject());
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			if (array != null && array.size()>0) {
				JSONObject o1 = array.getJSONObject(0);
				sessionMap.put("model", o1);
			} 
		}
		request.getSession().setAttribute("sessionMap", sessionMap);
		object.put("code", 0);
		return object;
	}
		
}
