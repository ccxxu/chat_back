package club.mzwh.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataManager {

	public static void sysIndex() {
		String query = "db.collection(\"sys_tables\").where({'is_valid':true}).limit(1000).orderBy('orderno', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONArray msg = new JSONArray();
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				String _id = item.getString("_id");
				String pid = item.getString("pid");
				if ("root".equals(pid)) {
					JSONArray a = new JSONArray();
					for (int k = 0; k < array.size(); k++) {
						JSONObject item0 = array.getJSONObject(k);
						String pi = item0.getString("pid");
						if (pi.equals(_id)) {
							a.add(item0);
						}
					}
					item.put("courses", a);
					item.put("hiddena", true);
					msg.add(item);
				}
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", msg);
			String sql = "db.collection(\"sys_view\").doc(\"sys_index\").update({\"data\":"+jsonObject.toString()+"})";
			HttpUtil.updateDatabase(sql);
		}
	}
	
	public static void bak2() {
		
		String sqlDel = "db.collection(\"sys_tables\").where({'pid':'db20200227'}).remove()";
		HttpUtil.delDatabase(sqlDel);
		
		String query = "db.collection(\"sys_tables_bak\").limit(1000).orderBy('orderno', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONArray msg = new JSONArray();
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				item.put("pid", "db20200227");
				msg.add(item);
			}
			String sql = "db.collection(\"sys_tables\").add({\"data\":"+msg.toString()+"})";
			HttpUtil.addDatabase(sql);
		}
	}
	
	public static void updatePathFile() {
		String query = "db.collection(\"sys_msg\").limit(500).orderBy('orderno', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				String _id = item.getString("_id");
				
				String type = item.getString("type");
				
				if ("jpg".equals(type) || "mp3".equals(type)) {
					String src = item.getString("src");
					String filepath = src.substring(src.indexOf("/mp3_jpg"));
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("filepath", filepath);
					jsonObject.put("src", "http://211.159.180.93"+filepath);
					String sql = "db.collection(\"sys_msg\").doc(\""+_id+"\").update({\"data\":"+jsonObject.toString()+"})";
					HttpUtil.updateDatabase(sql);
				}
				
			}
			
		}
	}
	
	public static void updateData(String tb) {
		String query = "db.collection(\"sys_msg\").where({'is_valid':true,\"tbname\":\""+tb+"\"}).limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", array);
			String sql = "db.collection(\"sys_view\").doc(\""+tb+"\").update({\"data\":"+jsonObject.toString()+"})";
			HttpUtil.updateDatabase(sql);
		}
	}
	
	public static void getAllCourses() {
		String query = "db.collection(\"sys_tables\").where({'is_valid':true}).limit(1000).orderBy('orderno', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				String pid = item.getString("pid");
				if ("db20200227".equals(pid) || "db1583341410273".equals(pid) || "db1583486325295".equals(pid) || "db1583632628305".equals(pid)) {
					String tbname = item.getString("tbname");
					updateData(tbname);
				}
			}
		}
	}
	
	//增加sys_view中_id
	public static void getAllCourses02() {
		String query = "db.collection(\"sys_tables\").limit(1000).orderBy('orderno', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			for (int i = 0; i < array.size(); i++) {
				JSONObject item = array.getJSONObject(i);
				String pid = item.getString("pid");
				if (!"root".equals(pid)) {
					String tbname = item.getString("tbname");
//					System.out.print(tbname+"\t");
					String sql = "db.collection(\"sys_view\").doc(\""+tbname+"\").get()";
					result = HttpUtil.getAllDatabases(sql);
					String str = "\"Total\":1";
					if (result.indexOf(str) < 0) {
						JSONObject jo = new JSONObject();
						jo.put("_id", tbname);
						JSONArray array2 = new JSONArray();
						array2.add(jo);
						sql = "db.collection(\"sys_view\").add({\"data\": "+array2.toString()+"})";
						result = HttpUtil.addDatabase(sql);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
//		updatePathFile();
		
//		getAllCourses();
//		sysIndex();
//		bak2();
//		updatePathFile();
//		getAllCourses02();
		
//		String query = "db.collection(\"sys_view\").limit(1000).get()";
//		String result = HttpUtil.getAllDatabases(query);
//		JSONObject obj = JSONObject.fromObject(result);
//		Object o = obj.get("data");
//		if (!("".equals(o) || o==null)) {
//			JSONArray array = JSONArray.fromObject(o);
//			String sql = "db.collection(\"sys_view\").add({\"data\":"+array.toString()+"})";
//			HttpILoveLifeReleaseUtil.addDatabase(sql);
//		}
	}
}
