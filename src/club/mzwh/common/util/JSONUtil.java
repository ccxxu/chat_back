package club.mzwh.common.util;

import net.sf.json.JSONObject;
import club.mzwh.common.model.MsgModel;

public class JSONUtil {

	public static String msgJson(String status,String info) throws Exception{
		MsgModel model = new MsgModel(status,info);
		JSONObject obj = JSONObject.fromObject(model);
		return obj.toString();
	}
	
	public static String msgJson(String status,String language,String info,String info_en) throws Exception{
		if("en".equals(language)){
			MsgModel model = new MsgModel(status,info);
			JSONObject obj = JSONObject.fromObject(model);
			return obj.toString();
		}else if("ec".equals(language)){
			MsgModel model = new MsgModel(status,info+" "+info_en);
			JSONObject obj = JSONObject.fromObject(model);
			return obj.toString();
		}else {
			MsgModel model = new MsgModel(status,info_en);
			JSONObject obj = JSONObject.fromObject(model);
			return obj.toString();
		}
	}
}
