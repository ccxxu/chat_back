package club.mzwh.chat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

/**  
 * @ClassName: TbModel
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年5月30日 
 */
@SuppressWarnings("serial")
@Entity
@Table(name="mz_sys_tables")
public class TbModel extends BaseMode{

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(name = "id", length = 32)
	private String id;
	
	@Column(name = "_id", length = 100)
	private String _id;
	
	@Column(name = "is_valid", length=10)
	private boolean is_valid;
	
	@Column(name = "name", length=255)
	private String name;
	
	@Column(name = "orderno", length=37)
	private long orderno;
	
	@Column(name = "pid", length=32)
	private String pid;
	
	@Column(name = "ipwd", length=255)
	private String ipwd;
	
	@Column(name = "pwd_flg", length=10)
	private boolean pwdFlg;
	
	@Column(name = "tbname", length=255)
	private String tbname;
	
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

	public boolean getIs_valid() {
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

	public long getOrderno() {
		return orderno;
	}

	public void setOrderno(long orderno) {
		this.orderno = orderno;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getIpwd() {
		return ipwd;
	}

	public void setIpwd(String ipwd) {
		this.ipwd = ipwd;
	}

	public boolean isPwdFlg() {
		return pwdFlg;
	}

	public void setPwdFlg(boolean pwdFlg) {
		this.pwdFlg = pwdFlg;
	}

	public String getTbname() {
		return tbname;
	}

	public void setTbname(String tbname) {
		this.tbname = tbname;
	}
	
}
