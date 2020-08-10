package club.mzwh.chat.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import club.mzwh.common.util.HttpUtil;
import club.mzwh.common.util.StringUtil;
import club.mzwh.common.util.TableDataInfo;


/**
* <p>版权所有:(C)2013-2020</p>
* @作者: ccx
* @日期: 2020-01-20 下午4:21:36
* @描述: [UserAction]云课列表
*/
@Controller
public class TbsNewAction {
	Logger log = Logger.getLogger(TbsNewAction.class);
	
	@RequestMapping(value = "/tbs/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap) 
			throws Exception {
		return new ModelAndView("/tbs/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/tbs/list", method = RequestMethod.POST)
	public TableDataInfo listPost(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String page = request.getParameter("pageNum");
		String rows = request.getParameter("pageSize");
		int pageSize = Integer.parseInt(rows);
		int pageNum = Integer.parseInt(page);
		int startRecord = (pageNum-1)*pageSize;
		String query = "db.collection(\"sys_tables_bak\").orderBy('orderno', 'asc').limit("+pageSize+").skip("+startRecord+").get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		List<JSONObject> list = new ArrayList<JSONObject>();
		TableDataInfo tdi = new TableDataInfo();
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			
			for (int i = 0; i < array.size(); i++) {
				list.add(array.getJSONObject(i));
			}
		}
		o = obj.get("pager");
		if (!("".equals(o) || o==null)) {
			JSONObject obj2 = JSONObject.fromObject(o);
			tdi.setTotal(Long.parseLong(obj2.get("Total")+""));
		} else {
			tdi.setTotal(0);
		}
		tdi.setRows(list);
		return tdi;
	}
	
	@RequestMapping(value = "/tbs/add", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap) 
			throws Exception {
		return new ModelAndView("/tbs/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/tbs/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	String sql = "db.collection(\"sys_tables_bak\").doc(\""+id+"\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		JSONArray array = JSONArray.fromObject(obj.get("data"));
		if (array != null && array.size()>0) {
			mmap.put("model", array.getJSONObject(0));
		} else {
			mmap.put("model", new JSONObject());
		}
        return new ModelAndView("/tbs/edit", mmap);
    }
	
	@ResponseBody
	@RequestMapping(value = "/tbs/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("_id");
		String name = request.getParameter("name");
		String orderno = request.getParameter("orderno");
		String isValid = request.getParameter("is_valid");
		String ipwd = request.getParameter("ipwd");
		String pwdFlg = request.getParameter("pwdFlg");
		String src = request.getParameter("src");
		
		String tbname = "tb"+System.currentTimeMillis();
		
		if ("".equals(id) || id==null) {
			long no = 0;
			try {
				no = Long.parseLong(orderno);
			} catch (NumberFormatException e) {
				no = System.currentTimeMillis();
			}
			JSONObject obj = new JSONObject();
			obj.put("tbname", tbname);
			obj.put("is_valid", true);
			obj.put("name", name);
			obj.put("orderno", no);
			obj.put("ipwd", ipwd);
			obj.put("pwdFlg", false);
			JSONArray array = new JSONArray();
			array.add(obj);
			String sql = "db.collection(\"sys_tables_bak\").add({\"data\": "+array.toString()+"})";
			String result = HttpUtil.addDatabase(sql);
			System.out.println(result);
			JSONObject jo = new JSONObject();
			jo.put("_id", tbname);
			JSONArray array2 = new JSONArray();
			array2.add(jo);
			sql = "db.collection(\"sys_view\").add({\"data\": "+array2.toString()+"})";
			result = HttpUtil.addDatabase(sql);
		} else {
			JSONObject obj = new JSONObject();
			if ("json".equals(src)) {
				if (!("".equals(isValid) || isValid == null)) {
					obj.put("is_valid", Boolean.valueOf(isValid));
				}
				if (StringUtil.isNotNullOrEmpty(pwdFlg)) {
					obj.put("pwdFlg", Boolean.valueOf(pwdFlg));
				}
			} else if ("form".equals(src)) {
				if (!("".equals(name) || name == null)) {
					obj.put("name", name);
				}
				if (!("".equals(orderno) || orderno == null)) {
					long no = 0;
					try {
						no = Long.parseLong(orderno);
					} catch (NumberFormatException e) {
						no = System.currentTimeMillis();
					}
					obj.put("orderno", no);
				}
				obj.put("ipwd", ipwd);
			}
			String sql = "db.collection(\"sys_tables_bak\").doc(\""+id+"\").update({\"data\":"+obj.toString()+"})";
			HttpUtil.updateDatabase(sql);
		}
		
		updateData();
		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "保存成功！！！");
		return object;
	}
	
	@ResponseBody
	@RequestMapping(value="/tbs/remove", method = RequestMethod.POST)
    public Object remove(HttpServletRequest request, String id, String tb) {
		int count = 0;
		if(!("".equals(tb) || tb==null)) {
			String query = "db.collection(\"sys_msg\").where({\"tbname\":\""+tb+"\"}).count()";
			String result = HttpUtil.getAllDatabaseCount(query);
			JSONObject obj = JSONObject.fromObject(result);
			Object o = obj.get("count");
			if (!("".equals(o) || o == null)) {
				count = Integer.valueOf(o+"");
			}
		}
		JSONObject object = new JSONObject();
		if(count == 0) {
			if(!("".equals(tb) || tb==null)) {
				HttpUtil.delCollection(tb);
			}
			String sql = "db.collection(\"sys_tables_bak\").doc(\""+id+"\").remove()";
			HttpUtil.delDatabase(sql);
			
			updateData();
			
			object.put("code", 0);
			object.put("msg", "删除成功记录！！！");
		} else {
			object.put("code", -1);
			object.put("msg", "当前云课下有消息内容，暂时不能删除！！！");
		}
		
		return object;
    }
	
	private void updateData() {
		String query = "db.collection(\"sys_tables_bak\").where({'is_valid':true}).limit(500).orderBy('orderno', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", array);
			String sql = "db.collection(\"sys_view\").doc(\"sys_tables\").update({\"data\":"+jsonObject.toString()+"})";
			HttpUtil.updateDatabase(sql);
		}
		
	}

}
