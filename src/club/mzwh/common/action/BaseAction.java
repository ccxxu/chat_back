package club.mzwh.common.action;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface BaseAction {
	/**
	 * 列表
	 * 
	 * @param fdid
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(@RequestParam(value = "fdid") String fdid, ModelMap modelMap) throws Exception;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object listPost(@RequestParam(value = "fdid") String fdid) throws Exception;

}
