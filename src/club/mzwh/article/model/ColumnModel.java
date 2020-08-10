package club.mzwh.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

/**
* <p>版权所有:(C)2013-2020</p>
* @作者: ccx
* @日期: 2013-4-21 下午12:53:05
* @描述: [ColumnModel]请在此简要描述类的功能
*/
@SuppressWarnings("serial")
@Entity
@Table(name = "mz_column")
public class ColumnModel extends BaseMode {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length = 32)
	private String id;
	
	// 栏目父id
	@Column(length=32)
	private String pid;
	
	/**
	 * 栏目主键
	 */
	@Column(name="id_key", length=32)
	private String key;
	
	// 是否展开
	@Column(length=10)
	private String state;
	
	// 栏目名字
	@Column(length=300)
	private String name;
	
	// 英文栏目名字
	@Column(name = "name_en", length=300)
	private String nameEN;
	
	// 英文栏目全名字
	@Column(name = "full_name_en", length=500)
	private String fullNameEN;
	
	// 栏目全名
	@Column(name = "full_name", length=1000)
	private String fullName;
	
	// 栏目描述
	@Column(name = "column_desc", length=600)
	private String columnDesc;
	
	// 树形结构
	@Column(length=600)
	private String hierarchical;
	
	// 文章排序
	@Column(name="open_type", length=100)
	private String openType;
	
	// 栏目图片
	@Column(name = "column_img", length=500)
	private String columnImg;
	
	// 列表框架模板
	@Column(length=30)
	private String template;
	
	// 每页文章数
	@Column(length=3)
	private String perpage;
	
	// 文章类型
	/** category ：[0：静态栏目(与staticUrl连用),1:资讯信息   2:图片新闻  3：视频栏目  4：音频栏目]  **/
	@Column(length=10)
	private String category;
	
	//栏目级别
	@Column(nullable = true,length=4)
	private int level;
	
	// 节点排序
	@Column(name="order_num", length=4)
	private Integer orderNum;
	
	// 文章页模板
	@Column(name="article_template", length=30)
	private String articleTemplate;
	
	// 列表模板
	@Column(name="list_template",length=30)
	private String listTemplate;
	
	//列表页显示格式
	@Column(name="format_date_view", length=30)
	private String formatDateView;
	
	/** staticUrl ：[静态页面地址]  **/
	@Column(name = "static_url", length=500)
	private String staticUrl;
	
	/**是否在前台显示 1：是    0：否**/
	@Column(name = "view_font", length=2)
	private String viewFront;
	
	@Column(name = "col_key", length=30)
	private String colKey;
	
	@Column(name = "col_value", length=30)
	private String colValue;
	
	@Column(name = "pre_code")
	private String preCode;
	
	@Column(name = "body_code")
	private String bodyCode;
	
	@Column(name = "post_code")
	private String postCode;
	
	@Column(name = "has_sub")
	private Integer hasSub;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEN() {
		return this.nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getFullNameEN() {
		return this.fullNameEN;
	}

	public void setFullNameEN(String fullNameEN) {
		this.fullNameEN = fullNameEN;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getColumnDesc() {
		return this.columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}

	public String getHierarchical() {
		return this.hierarchical;
	}

	public void setHierarchical(String hierarchical) {
		this.hierarchical = hierarchical;
	}
	
	/**
	 * @return the openType
	 */
	public String getOpenType() {
		return this.openType;
	}
	
	/**
	 * @param openType the openType to set
	 */
	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getColumnImg() {
		return this.columnImg;
	}

	public void setColumnImg(String columnImg) {
		this.columnImg = columnImg;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getPerpage() {
		return this.perpage;
	}

	public void setPerpage(String perpage) {
		this.perpage = perpage;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getArticleTemplate() {
		return this.articleTemplate;
	}

	public void setArticleTemplate(String articleTemplate) {
		this.articleTemplate = articleTemplate;
	}

	public String getListTemplate() {
		return this.listTemplate;
	}

	public void setListTemplate(String listTemplate) {
		this.listTemplate = listTemplate;
	}

	public String getFormatDateView() {
		return this.formatDateView;
	}

	public void setFormatDateView(String formatDateView) {
		this.formatDateView = formatDateView;
	}

	public String getStaticUrl() {
		return this.staticUrl;
	}

	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}

	public String getViewFront() {
		return this.viewFront;
	}

	public void setViewFront(String viewFront) {
		this.viewFront = viewFront;
	}
	
	/**
	 * @return the colKey
	 */
	public String getColKey() {
		return colKey;
	}
	
	/**
	 * @param colKey the colKey to set
	 */
	public void setColKey(String colKey) {
		this.colKey = colKey;
	}
	
	/**
	 * @return the colValue
	 */
	public String getColValue() {
		return colValue;
	}
	
	/**
	 * @param colValue the colValue to set
	 */
	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
	
	/**
	 * @return the preCode
	 */
	public String getPreCode() {
		return this.preCode;
	}
	
	/**
	 * @param preCode the preCode to set
	 */
	public void setPreCode(String preCode) {
		this.preCode = preCode;
	}
	
	/**
	 * @return the bodyCode
	 */
	public String getBodyCode() {
		return this.bodyCode;
	}
	
	/**
	 * @param bodyCode the bodyCode to set
	 */
	public void setBodyCode(String bodyCode) {
		this.bodyCode = bodyCode;
	}
	
	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return this.postCode;
	}
	
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	/**
	 * @return the hasSub
	 */
	public Integer getHasSub() {
		return this.hasSub;
	}
	
	/**
	 * @param hasSub the hasSub to set
	 */
	public void setHasSub(Integer hasSub) {
		this.hasSub = hasSub;
	}
	
}
