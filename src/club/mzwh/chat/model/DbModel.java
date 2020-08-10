package club.mzwh.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

/**  
 * 云课消息类
 * @ClassName: DbModel
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年5月31日 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="mz_sys_msg")
public class DbModel extends BaseMode{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(name = "id", length = 32)
	private String id;
	
	@Column(name = "_id", length = 100)
	private String _id;
	
	@Column(name = "_openid", length=255)
	private String _openid;
	
	@Column(name = "avatar_url", length=255)
	private String avatarUrl;
	
	@Column(name = "file_id", length=255)
	private String fileId;
	
	@Column(name = "filepath", length=255)
	private String filepath;
	
	@Column(name = "content", length=4000)
	private String content;
	
	@Column(name = "is_valid", length=10)
	private boolean is_valid;
	
	@Column(name = "name", length=255)
	private String name;
	
	@Column(name = "nick_name", length=255)
	private String nickName;
	
	@Column(name = "src", length=255)
	private String src;
	
	@Column(name = "tbname", length=255)
	private String tbname;
	
	@Column(name = "title", length=255)
	private String title;
	
	@Column(name = "type", length=10)
	private String type;
	
	@Column(name = "xuhao", length=37)
	private long xuhao;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_openid() {
		return _openid;
	}

	public void set_openid(String _openid) {
		this._openid = _openid;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isIs_valid() {
		return is_valid;
	}

	public void setIs_valid(boolean is_valid) {
		this.is_valid = is_valid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTbname() {
		return tbname;
	}

	public void setTbname(String tbname) {
		this.tbname = tbname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getXuhao() {
		return xuhao;
	}

	public void setXuhao(long xuhao) {
		this.xuhao = xuhao;
	}
	
	
	
}
