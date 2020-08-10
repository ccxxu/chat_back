package club.mzwh.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import club.mzwh.common.util.DateFormatUtil;

/**
 * 
 * @author ccxxu
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseMode implements Serializable {
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	
	/**
	 * 创建人
	 */
	@Column(name = "creator", length = 100)
	private String creator;
	
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	
	/**
	 * 更新人
	 */
	@Column(name = "updator", length=100)
	private String updator;
	
	/**
	 * string 格式
	 */
	@Transient
	private String createTimeString;
	
	/**
	 * string 格式
	 */
	@Transient
	private String updateTimeString;

	/**
	 * 设置
	 * 
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeString = DateFormatUtil.DateToString(createTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 设置
	 * 
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeString = DateFormatUtil.DateToString(updateTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取
	 * 
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * 设置
	 * 
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 获取
	 * 
	 * @return the updator
	 */
	public String getUpdator() {
		return updator;
	}

	/**
	 * 设置
	 * 
	 * @param updator
	 *            the updator to set
	 */
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	/**
	 * 获取
	 * 
	 * @return the createTimeString
	 */
	public String getCreateTimeString() {
		return DateFormatUtil.DateToString(createTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 设置
	 * 
	 * @param createTimeString
	 *            the createTimeString to set
	 */
	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}

	/**
	 * 获取
	 * 
	 * @return the updateTimeString
	 */
	public String getUpdateTimeString() {
		return DateFormatUtil.DateToString(updateTime, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 设置
	 * 
	 * @param updateTimeString
	 *            the updateTimeString to set
	 */
	public void setUpdateTimeString(String updateTimeString) {
		
		this.updateTimeString = updateTimeString;
	}

	/**
	 * 获取
	 * 
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取
	 * 
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
