package com.test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import club.mzwh.common.util.HttpUtil;

public class TestUpdate {

	public static void test01() {
		String tb = "tb1582273608984";
		String query = "db.collection(\""+tb+"\").limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println("size="+array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject j = array.getJSONObject(i);
				j.put("tbname", tb);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", array);
			String sql = "db.collection(\"sys_msg\").add({\"data\":"+array.toString()+"})";
			System.out.println(sql);
			HttpUtil.addDatabase(sql);
		}
	}
	
	public static void test02() {
		JSONArray array = new JSONArray();
		for (int i = 0; i < 20; i++) {
			JSONObject j = new JSONObject();
			j.put("_id", i);
			j.put("tbname", i);
			array.add(j);
		}
		String sql = "db.collection(\"test\").add({\"data\":"+array.toString()+"})";
		System.out.println(sql);
		HttpUtil.addDatabase(sql);
	}
	
	public static void main(String[] args) {
//		t2o();
		
		t2o();
	}
	
	public static void t2o() {
		String query = "db.collection(\"sys_msg\").where({\"tbname\":\"tb1582643816301\"}).limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println("size="+array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj1 = array.getJSONObject(i);
				String sql = "db.collection(\"tb1582643816301\").add({\"data\":"+obj1.toString()+"})";
				System.out.println(sql);
				HttpUtil.addDatabase(sql);
			}
			
		}
	}
	
	public static void t2o123() {
		String query = "db.collection(\"sys_msg\").where({\"tbname\":\"tb1582020734963\"}).limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println("size="+array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj1 = array.getJSONObject(i);
				String sql = "db.collection(\"tb1582020734963\").add({\"data\":"+obj1.toString()+"})";
				System.out.println(sql);
				HttpUtil.addDatabase(sql);
			}
			
		}
	}
	
	public static void o2t() {
		String query = "db.collection(\"tb1582273608984\").limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println("size="+array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj1 = array.getJSONObject(i);
				String id = obj1.getString("_id");
				obj1.remove("_id");
				obj1.put("tbname", "tb1582273608984");
				String sql = "db.collection(\"sys_msg\").doc(\""+id+"\").update({\"data\":"+obj1.toString()+"})";
				System.out.println(sql);
				HttpUtil.updateDatabase(sql);
			}
			
		}
	}
	
	public static void o2t123456() {
		String query = "db.collection(\"sys_msg\").where({'is_valid':true,\"tbname\":\"tb1582020734963\"}).limit(1000).orderBy('xuhao', 'asc').get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", array);
			String sql = "db.collection(\"sys_view\").doc(\"tb1582020734963\").update({\"data\":"+jsonObject.toString()+"})";
			
				System.out.println(sql);
				HttpUtil.updateDatabase(sql);
			
		}
	}
	
}
