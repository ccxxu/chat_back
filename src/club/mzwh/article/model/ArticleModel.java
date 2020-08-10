package club.mzwh.article.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import club.mzwh.common.model.BaseMode;

@SuppressWarnings("serial")
@Entity
@Table(name="mz_article")
public class ArticleModel extends BaseMode {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid.hex")
	@Column(length = 32)
	private String id;
	
	@Column(name = "title", length=500)
	private String title;					//文章标题
	
	@Column(name = "sub_title", length=500)
	private String subTitle;				//副标题
	
	@Column(name = "short_title", length=500)
	private String shortTitle;				//缩略标题
	
	@Column(name = "title_color", length=20,nullable=true)
	private String titleColor;				//标题颜色
	
	@Column(name = "source", length=500,nullable=true)
	private String source;					//来源
	
	@Column(name="main_col_key")
	private String mainColKey;
	
	@Column(name="slave_col_key")
	private String slaveColKey;
	
	@Column(length=500,nullable=true)
	private String keyword;					//关键词  逗号隔开
	
	@Column(name = "info_type", length=2)
	private String infoType;				//信息类型		0：普通文章    缺省为0
	
	@Column(length=1500)
	private String digest;					//摘要
	
	@Column(length=100)
	private String author;					//作者
	
	@Column(name = "exe_editor", length=100)
	private String exeEditor;			//责编
	
	@Column(name = "article_type", length=2)
	private String articleType;				//文章录入类型    1：原创   2：编译  3：摘编  4：转载
	
	@Column(name = "image_url", length=500)
	private String imageUrl;					//焦点图片地址
	
	@Column(name = "video_url", length=500)
	private String videoUrl;					//视频地址
	
	@Column(name = "audio_url", length=500)
	private String audioUrl;					//音频地址
	
	@Column(name = "redirect_url", length=500)
	private String redirectUrl;					//文章跳转地址
	
	@Column(name = "read_role", length=2)
	private String readRole;  	//查看权限        1:都可以查看   2：授权查看
	
	@Column(name = "is_share", length=2)
	private String isShare; //是否为同步数据 1：是同步数据；0：不是同步数据
	
	/** 
	* @Fields click_num : 点击次数 
	*/ 
	@Column(name = "click_name", length=10)
	private Integer clickNum;
	
	@Column(name = "order_num", length = 10)
	private Integer orderNum;
	
	@Column(name = "pub_date", length=20)
	private String pubDate;	//发布时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * @return the mainColKey
	 */
	public String getMainColKey() {
		return this.mainColKey;
	}
	
	/**
	 * @param mainColKey the mainColKey to set
	 */
	public void setMainColKey(String mainColKey) {
		this.mainColKey = mainColKey;
	}
	
	/**
	 * @return the slaveColKey
	 */
	public String getSlaveColKey() {
		return this.slaveColKey;
	}
	
	/**
	 * @param slaveColKey the slaveColKey to set
	 */
	public void setSlaveColKey(String slaveColKey) {
		this.slaveColKey = slaveColKey;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getExeEditor() {
		return exeEditor;
	}

	public void setExeEditor(String exeEditor) {
		this.exeEditor = exeEditor;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getReadRole() {
		return readRole;
	}

	public void setReadRole(String readRole) {
		this.readRole = readRole;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
}
