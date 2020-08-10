package club.mzwh.common.model;

/**
 * @author ccx
 * 2015-8-26 下午1:59:01
 * 描述：【返回消息对象 用户json处理】
 */
public class MsgModel {

	private String code;
	private String msg;
	
	public MsgModel(String code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
