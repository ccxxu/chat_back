package club.mzwh.chat.action;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import club.mzwh.chat.model.DbModel;
import club.mzwh.chat.model.TbModel;
import club.mzwh.chat.service.DbService;
import club.mzwh.chat.service.TbService;
import club.mzwh.common.util.HttpUtil;
import club.mzwh.common.util.StringUtil;

/**  
 * @ClassName: DataAction
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年5月31日 
 * 6+16+14+26+37+10+2=6+30+63+12=111
 */
@Controller
public class DataAction {
	
	@Autowired
	private TbService tbService;
	
	@Autowired
	private DbService dbService;
	
	@ResponseBody
	@RequestMapping(value="/data/msg", method = RequestMethod.GET)
    public Object data(HttpServletRequest request) {
		
		String skip = request.getParameter("skip");
		
		String query = "db.collection(\"sys_msg\").skip("+skip+").limit(1000).get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println(array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject json = array.getJSONObject(i);
				DbModel dm = new DbModel();
				String _id = json.getString("_id");
				dm.set_id(_id);
				if (StringUtil.isNotNullOrEmpty(json.get("_openid"))) {
					String _openid = json.get("_openid")+"";
					dm.set_openid(_openid);
				}
				String avatarUrl = json.getString("avatarUrl");
				dm.setAvatarUrl(avatarUrl);
				if (StringUtil.isNotNullOrEmpty(json.get("fileId"))) {
					String fileId = json.get("fileId")+"";
					dm.setFileId(fileId);
				}
				if (StringUtil.isNotNullOrEmpty(json.get("filepath"))) {
					String filepath = json.get("filepath")+"";
					dm.setFilepath(filepath);
				}
				boolean is_valid = json.getBoolean("is_valid");
				dm.setIs_valid(is_valid);
				if (StringUtil.isNotNullOrEmpty(json.get("name"))) {
					String name = json.get("name")+"";
					dm.setName(name);
				}
				String nickName = json.getString("nickName");
				dm.setNickName(nickName);
				if (StringUtil.isNotNullOrEmpty(json.get("src"))) {
					String src = json.get("src")+"";
					dm.setSrc(src);
				}
				String tbname = json.getString("tbname");
				dm.setTbname(tbname);
				String title = json.getString("title");
				dm.setTitle(title);
				String type = json.getString("type");
				dm.setType(type);
				long xuhao = json.getLong("xuhao");
				dm.setXuhao(xuhao);
				if (StringUtil.isNotNullOrEmpty(json.get("content"))) {
					String content = json.get("content")+"";
					dm.setContent(content);
				}
				try {
					this.dbService.save(dm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "删除消息成功！！！");
		return object;
    }
	
	@ResponseBody
	@RequestMapping(value="/data/tb", method = RequestMethod.GET)
    public Object data2(HttpServletRequest request) {
		
		String query = "db.collection(\"sys_tables\").skip(0).limit(1000).get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println(array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject json = array.getJSONObject(i);
				TbModel tb = new TbModel();
				String _id = json.getString("_id");
				tb.set_id(_id);
				if (StringUtil.isNotNullOrEmpty(json.get("ipwd"))) {
					String ipwd = json.get("ipwd")+"";
					tb.setIpwd(ipwd);
				}
				boolean is_valid = json.getBoolean("is_valid");
				tb.setIs_valid(is_valid);
				if (StringUtil.isNotNullOrEmpty(json.get("name"))) {
					String name = json.get("name")+"";
					tb.setName(name);
				}
				if (StringUtil.isNotNullOrEmpty(json.get("tbname"))) {
					String tbname = json.getString("tbname");
					tb.setTbname(tbname);
				}
				String pid = json.getString("pid");
				tb.setPid(pid);
				if (StringUtil.isNotNullOrEmpty(json.get("pwdFlg"))) {
					boolean pwdFlg = json.getBoolean("pwdFlg");
					tb.setPwdFlg(pwdFlg);
				}
				long xuhao = json.getLong("orderno");
				tb.setOrderno(xuhao);
				try {
					this.tbService.save(tb);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		JSONObject object = new JSONObject();
		object.put("code", 0);
		object.put("msg", "删除消息成功！！！");
		return object;
    }
	
	public static void main(String[] args) {
		String query = "db.collection(\"sys_msg\").skip(1000).limit(1000).get()";
		String result = HttpUtil.getAllDatabases(query);
		
		JSONObject obj = JSONObject.fromObject(result);
		Object o = obj.get("data");
		if (!("".equals(o) || o==null)) {
			JSONArray array = JSONArray.fromObject(o);
			System.out.println(array.size());
			for (int i = 0; i < array.size(); i++) {
				JSONObject json = array.getJSONObject(i);
				DbModel dm = new DbModel();
				String _id = json.getString("_id");
				dm.set_id(_id);
				String _openid = json.get("_openid")+"";
				dm.set_openid(_openid);
				String avatarUrl = json.getString("avatarUrl");
				dm.setAvatarUrl(avatarUrl);
				String fileId = json.get("fileId")+"";
				dm.setFileId(fileId);
				String filepath = json.get("filepath")+"";
				dm.setFilepath(filepath);
				boolean is_valid = json.getBoolean("is_valid");
				dm.setIs_valid(is_valid);
				String name = json.get("name")+"";
				dm.setName(name);
				String nickName = json.getString("nickName");
				dm.setNickName(nickName);
				String src = json.get("src")+"";
				dm.setSrc(src);
				String tbname = json.getString("tbname");
				dm.setTbname(tbname);
				String title = json.getString("title");
				dm.setTitle(title);
				String type = json.getString("type");
				dm.setType(type);
				long xuhao = json.getLong("xuhao");
				dm.setXuhao(xuhao);
				String content = json.get("content")+"";
				dm.setContent(content);
				
			}
			
		}
	}

}
