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

import club.mzwh.common.util.DataManager;
import club.mzwh.common.util.HttpUtil;
import club.mzwh.common.util.StringUtil;


/**
* <p>版权所有:(C)2013-2020</p>
* @作者: ccx
* @日期: 2020-01-20 下午4:21:36
* @描述: [UserAction]云课列表
*/
@Controller
public class TbsAction {
	Logger log = Logger.getLogger(TbsAction.class);
	
	@RequestMapping(value = "/tree/list", method = RequestMethod.GET)
	public ModelAndView listGet(ModelMap modelMap) 
			throws Exception {
		return new ModelAndView("/tree/list", modelMap);
	}

	@ResponseBody
	@RequestMapping(value = "/tree/list", method = RequestMethod.POST)
	public List<JSONObject> listPost(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		String query = "db.collection(\"sys_tables\").orderBy('orderno', 'asc').limit(500).get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		List<JSONObject> list = new ArrayList<JSONObject>();
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			
			for (int i = 0; i < array.size(); i++) {
				JSONObject jo = array.getJSONObject(i);
				String pid = jo.getString("pid");
				String id = jo.getString("_id");
				String str = "\\\"pid\\\":\\\""+id+"\\\"";
				jo.put("isDel", true);
				if ("root".equals(pid)) {
					if(result.indexOf(str)>-1) {
						jo.put("isDel", false);
					} 
				}
				list.add(jo);
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/tree/add", method = RequestMethod.GET)
	public ModelAndView addGet(ModelMap modelMap, String pid) 
			throws Exception {
		modelMap.put("pid", pid);
		return new ModelAndView("/tree/add", modelMap);
	}
	
	/**
     * 修改角色
     */
    @RequestMapping(value = "/tree/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") String id, ModelMap mmap) {
    	String sql = "db.collection(\"sys_tables\").doc(\""+id+"\").get()";
		String result = HttpUtil.getAllDatabases(sql);
		JSONObject obj = JSONObject.fromObject(result);
		JSONArray array = JSONArray.fromObject(obj.get("data"));
		if (array != null && array.size()>0) {
			mmap.put("model", array.getJSONObject(0));
		} else {
			mmap.put("model", new JSONObject());
		}
		String sql01 = "db.collection(\"sys_tables\").where({'pid':'root','is_valid':true}).get()";
		String result01 = HttpUtil.getAllDatabases(sql01);
		JSONObject obj01 = JSONObject.fromObject(result01);
		JSONArray array01 = JSONArray.fromObject(obj01.get("data"));
		mmap.put("cate", array01);
        return new ModelAndView("/tree/edit", mmap);
    }
	
	@ResponseBody
	@RequestMapping(value = "/tree/save", method = RequestMethod.POST)
	public Object save(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("_id");
		String name = request.getParameter("name");
		String orderno = request.getParameter("orderno");
		String isValid = request.getParameter("is_valid");
		String pid = request.getParameter("pid");
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
			if ("root".equals(pid)) {
				JSONObject obj = new JSONObject();
				obj.put("_id", "db"+System.currentTimeMillis());
				obj.put("is_valid", true);
				obj.put("name", name);
				obj.put("orderno", no);
				obj.put("pid", "root");
				JSONArray array = new JSONArray();
				array.add(obj);
				String sql = "db.collection(\"sys_tables\").add({\"data\": "+array.toString()+"})";
				HttpUtil.addDatabase(sql);
			} else {
				JSONObject obj = new JSONObject();
				obj.put("tbname", tbname);
				obj.put("is_valid", true);
				obj.put("name", name);
				obj.put("orderno", no);
				obj.put("pid", pid);
				obj.put("pwdFlg", false);
				obj.put("ipwd", ipwd);
				JSONArray array = new JSONArray();
				array.add(obj);
				String sql = "db.collection(\"sys_tables\").add({\"data\": "+array.toString()+"})";
				HttpUtil.addDatabase(sql);
				
				JSONObject jo = new JSONObject();
				jo.put("_id", tbname);
				JSONArray array2 = new JSONArray();
				array2.add(jo);
				sql = "db.collection(\"sys_view\").add({\"data\": "+array2.toString()+"})";
				HttpUtil.addDatabase(sql);
			}
			
		} else {
			JSONObject obj = new JSONObject();
			obj.put("pid", pid);
			if ("json".equals(src)) {
				if (!("".equals(isValid) || isValid == null)) {
					obj.put("is_valid", Boolean.valueOf(isValid));
				}
				if (StringUtil.isNotNullOrEmpty(pwdFlg)) {
					obj.put("pwdFlg", Boolean.valueOf(pwdFlg));
				}
			} else {
				if (!("".equals(name) || name == null)) {
					obj.put("name", name);
				}
				if (StringUtil.isNotNullOrEmpty(ipwd)) {
					obj.put("ipwd", ipwd);
				}
				if (!("".equals(orderno) || orderno == null)) {
					long no = 0;
					try {
						no = Long.parseLong(orderno);
					} catch (NumberFormatException e) {
						no = System.currentTimeMillis();
					}
					obj.put("orderno", no);
				} else {
					obj.put("orderno", System.currentTimeMillis());
				}
			}
			String sql = "db.collection(\"sys_tables\").doc(\""+id+"\").update({\"data\":"+obj.toString()+"})";
			HttpUtil.updateDatabase(sql);
			sql = "db.collection(\"sys_tables\").doc(\""+id+"\").update({\"data\":"+obj.toString()+"})";
			HttpUtil.updateDatabase(sql);
			
		}
		
		//更新视图
		DataManager.sysIndex();
		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "操作成功!!!");
		return object;
	}
	
	@ResponseBody
	@RequestMapping(value="/tree/remove", method = RequestMethod.POST)
    public Object remove(String id, String tb) {
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
			String sql = "db.collection(\"sys_tables\").doc(\""+id+"\").remove()";
			HttpUtil.delDatabase(sql);
			
			sql = "db.collection(\"sys_view\").doc(\""+tb+"\").remove()";
			HttpUtil.delDatabase(sql);
			
			DataManager.sysIndex();
			
			object.put("code", 0);
			object.put("msg", "删除记录成功！！！");
		} else {
			object.put("code", -1);
			object.put("msg", "当前云课下有消息内容，暂时不能删除！！！");
		}
		
		return object;
    }
	
}
