package club.mzwh.security.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

/**
 * 
 * @author: ccx
 * @version:2016-9-12 上午9:03:14 类说明 角色类
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mz_resource")
public class ResourceModel extends BaseMode {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length=32)
	private String fdid;

	/**
	 * name
	 */
	@Column(name = "name", length=150)
	private String name;
	
	// 地址
	@Column(name = "url", length=500)
	private String url;
	
	// id
	@Column(length=50)
	private String id;
	
	// 树id
	@Column(length=32)
	private String pid;
	
	//是否作为菜单显示
	@Column(name="show_menu",length=10)
	private String showMenu;
	
	//导航类型
	@Column(name="show_type",length=10)
	private String type="menu";
	
	//排序
	@Column(name="order_num",length=10)
	private Integer orderNum;
	
	@Column(length=255)
	private String target;
	
	@Column(length=255)
	private String perms;
	
	@Column(length=255)
	private String icon;
	
	@Column(length=255)
	private String remark;
	
	@Column(length=2)
	private String visible;

	// 是否展开
	@Column(length=10)
	private String state;
	
	// 是否有子节点
	@Column(name = "has_sub", length=10)
	private String hasSub;
	
	//树形结构
	@Column(length=600)
	private String  hierarchical;

	@Transient
	private List<ResourceModel> children = new ArrayList<ResourceModel>();
	
	public String getFdid() {
		return fdid;
	}

	public void setFdid(String fdid) {
		this.fdid = fdid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHasSub() {
		return hasSub;
	}

	public void setHasSub(String hasSub) {
		this.hasSub = hasSub;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHierarchical() {
		return hierarchical;
	}

	public void setHierarchical(String hierarchical) {
		this.hierarchical = hierarchical;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShowMenu() {
		return showMenu;
	}

	public void setShowMenu(String showMenu) {
		this.showMenu = showMenu;
	}

	public List<ResourceModel> getChildren() {
		return children;
	}
	
	public void setChildren(List<ResourceModel> children) {
		this.children = children;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getVisible() {
		return visible;
	}
	
	public void setVisible(String visible) {
		this.visible = visible;
	}
	
	public String getPerms() {
		return perms;
	}
	
	public void setPerms(String perms) {
		this.perms = perms;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
