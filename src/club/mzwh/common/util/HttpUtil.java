package club.mzwh.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class HttpUtil {
	
//	private final static String ENV = "ccx-1989-k8ae9";
//	private final static String APPID = "wx138bee0044e43003";
//	private final static String SECRET = "02ddb86d4a4845b072dbeca96bd7e2db";
//	public final static String PREFF_FILEID = "cloud://ccx-1989-k8ae9.6363-ccx-1989-k8ae9-1301187824/";
//	public final static String PREFF_HTTPS = "https://6363-ccx-1989-k8ae9-1301187824.tcb.qcloud.la/";
	private final static String ENV = "i-love-life-d9n85";
	public final static String APPID = "wx31d1a4c38d117c45";
	private final static String SECRET = "dc9943ad174bc8396eb4fffc73d86c31";
	public final static String PREFF_FILEID = "cloud://i-love-life-d9n85.692d-i-love-life-d9n85-1301263241/";
	public final static String OPENID = "otOzI5VPP5ce5MaOhYYTBkOk7rPk";
//	otOzI5cX-afWsXqS4fSNd-1rc5AU	
	
	public static final String[] SERVERIPS = {"http://211.159.180.93/chat02/", "https://692d-i-love-life-d9n85-1301263241.tcb.qcloud.la/",
		"https://6363-ccx-1989-k8ae9-1301187824.tcb.qcloud.la/", "https://6363-ccx-1993-r5ob2-1301187824.tcb.qcloud.la/"};
	
	public final static String PREFF_HTTPS = "http://211.159.180.93/mp3_jpg/";
	
	/**
	 * 向指定URL发送GET方法的请求
	 * @param url  发送请求的URL
	 * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",	"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			/*
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			*/
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {    // 使用finally块来关闭输入流
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url 发送请求的 URL
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");                    
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");//设置参数类型是json格式
			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
//			out = new PrintWriter(conn.getOutputStream());
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8), true);
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		} finally {
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			} catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 获取凭证
	 * @return
	 */
	public static String getAccessToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		String param = "grant_type=client_credential&appid="+APPID+"&secret="+SECRET;
		String s=sendGet(url, param);
		if (StringUtil.isNotNullOrEmpty(s)) {
			JSONObject obj = JSONObject.fromObject(s);
			return obj.get("access_token")+"";
		}
		return "";
	}
	
	/**
	 * 获取集合信息
	 * @return
	 */
	public static String getCollection() {
		JSONObject jsonObject3 = new JSONObject();
		jsonObject3.put("env", ENV);
		jsonObject3.put("limit",10);
		jsonObject3.put("offset", 0);
		String url3 = "https://api.weixin.qq.com/tcb/databasecollectionget?access_token="+getAccessToken();
		String s3 = sendPost(url3, jsonObject3.toString());
		return s3;
	}
	
	/**
	 * 添加集合
	 * @param collection  集合名称
	 * @return
	 */
	public static String addCollection(String collection) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("collection_name",collection);
		String url2 = "https://api.weixin.qq.com/tcb/databasecollectionadd?access_token="+getAccessToken();
		String s2 = sendPost(url2, jsonObject.toString());
		return s2;
	}
	
	/**
	 * 删除集合
	 * @param collection  集合名称
	 * @return
	 */
	public static String delCollection(String collection) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("collection_name",collection);
		String url2 = "https://api.weixin.qq.com/tcb/databasecollectiondelete?access_token="+HttpUtil.getAccessToken();
		String s2 = HttpUtil.sendPost(url2, jsonObject.toString());
		return s2;
	}
	
	/**
	 * 查询记录
	 * @param query
	 * @return
	 */
	public static String getAllDatabases(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("query",query);
		String url = "https://api.weixin.qq.com/tcb/databasequery?access_token="+getAccessToken();
		String result = sendPost(url, jsonObject.toString());
		return result;
	}
	
	/**
	 * 查询记录条数
	 * @param query
	 * @return
	 */
	public static String getAllDatabaseCount(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("query",query);
		String url = "https://api.weixin.qq.com/tcb/databasecount?access_token="+getAccessToken();
		String result = sendPost(url, jsonObject.toString());
		return result;
	}
	
	/**
	 * 插入记录
	 * @param query sql语句
	 * @return
	 */
	public static String addDatabase(String query) {
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("env", ENV);
//		String query = "db.collection(\"ceshi\").add({data: [{description: \"item1\",due: new Date(\"2019-09-09\"),"
//				+ "tags: [\"cloud\",\"database\"],location: new db.Geo.Point(113, 23),done: false}]})";
		jsonObject4.put("query",query);
		String url4 = "https://api.weixin.qq.com/tcb/databaseadd?access_token="+getAccessToken();
		String s4 = sendPost(url4, jsonObject4.toString());
		return s4;
	}
	
	/**
	 * 更新记录
	 * @param query sql语句
	 * @return
	 */
	public static String updateDatabase(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("query",query);
		String url = "https://api.weixin.qq.com/tcb/databaseupdate?access_token="+getAccessToken();
		String result = sendPost(url, jsonObject.toString());
		return result;
	}
	
	/**
	 * 删除记录
	 * @param query sql语句
	 * @return
	 */
	public static String delDatabase(String query) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("query",query);
		String url = "https://api.weixin.qq.com/tcb/databasedelete?access_token="+getAccessToken();
		String result = sendPost(url, jsonObject.toString());
		return result;
	}
	
	/**
	 * 删除云存储文件
	 * @param fileId 文件ID
	 * @return
	 */
	public static String delFile(String fileId) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("env", ENV);
		jsonObject.put("fileid_list", "[\""+fileId+"\"]");
		String url = "https://api.weixin.qq.com/tcb/batchdeletefile?access_token="+getAccessToken();
		String result = sendPost(url, jsonObject.toString());
		return result;
	}
	
	/**
	 * 获取上传文件链接并且上传文件
	 * @param path
	 * @param file
	 */
	public static void uploadFile(String path, String file) {
		JSONObject jsonObject4 = new JSONObject();
		jsonObject4.put("env", ENV);
		jsonObject4.put("path",path);
		String url4 = "https://api.weixin.qq.com/tcb/uploadfile?access_token="+getAccessToken();
		String s4 = sendPost(url4, jsonObject4.toString());
		JSONObject res = JSONObject.fromObject(s4);
		String url5 = res.get("url")+"";
		
        Map<String, String> textMap = new HashMap<String, String>();
        //可以设置多个input的name，value
        textMap.put("key", path);
        textMap.put("Signature", res.get("authorization")+"");
        textMap.put("x-cos-security-token", res.get("token")+"");
        textMap.put("x-cos-meta-fileid", res.get("cos_file_id")+"");
        //设置file的name，路径
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("file", file);
        String contentType = "";//image/png
        String ret = UploadUtil.formUpload(url5, textMap, fileMap,contentType);
        System.out.println(ret);
	}
	
	public static void main(String[] args) {	
		
//		addCollection("sys_tables");
		/*
		String sql1 = "db.collection(\"sys_tables\").add({data: [{tbname: \"ceshi\",is_valid:true,due: new Date(\"2019-09-09\"),"
				+ "name:\"测试\", orderno:1}]})";
		addDatabase(sql1);
		sql1 = "db.collection(\"sys_tables\").add({data: [{tbname: \"test\",is_valid:true,due: new Date(\"2019-09-09\"),"
				+ "name:\"互联网1\", orderno:2}]})";
		addDatabase(sql1);
		sql1 = "db.collection(\"sys_tables\").add({data: [{tbname: \"todos\",is_valid:true,due: new Date(\"2019-09-09\"),"
				+ "name:\"互联网2\", orderno:3}]})";
		addDatabase(sql1);
		*/
		/*
		String query = "db.collection(\"sys_tables\").orderBy('orderno', 'asc').limit(10).skip(0).get()";
		String result = getAllDatabases(query);
		System.out.println(result);
		*/
		
//		String sql = "db.collection(\"sys_tables\").get()";
//		String result = getAllDatabases(sql);
//		System.out.println(result);
		
		
//		getAccessToken();
		
		sendGet("https://692d-i-love-life-d9n85-1301263241.tcb.qcloud.la/mp3_jpg/WechatIMG242.png", "");
		
	}
}
