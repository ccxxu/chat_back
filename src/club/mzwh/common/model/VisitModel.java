/**  
 * @Title: VisitModel.java
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年3月14日 
 */  

package club.mzwh.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**  
 * 统计网站访问量
 * @ClassName: VisitModel
 * @Description: TODO(描述)
 * @author ccxxu
 * @date 2020年3月14日 
 */
@Entity
@Table(name="mz_visit")
public class VisitModel implements Serializable {
	
	private static final long serialVersionUID = -7966021482684782518L;

	@Id
	@Column(length=32)
	private String id;
	
	@Column(length=10)
	private Integer cnt;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the cnt
	 */
	public Integer getCnt() {
		return cnt;
	}
	
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	
}
