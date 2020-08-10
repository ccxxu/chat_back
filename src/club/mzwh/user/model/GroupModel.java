package club.mzwh.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

/**
 * 
 * @author ccxxu
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="mz_group")
public class GroupModel extends BaseMode {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length = 32)
	private String id;
		
	@Column(length = 255)
	private String name;

	@Column(length = 2)
	private String status;
	
	@Column(length = 255)
	private String descc;
	
	@Column(name = "order_num", length = 10)
	private Integer orderNum;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescc() {
		return descc;
	}
	
	public void setDescc(String descc) {
		this.descc = descc;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

}
