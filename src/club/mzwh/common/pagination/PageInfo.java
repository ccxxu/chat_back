package club.mzwh.common.pagination;

import java.io.Serializable;
import java.util.List;

/**
* @模块：[公用模块]
* @描述: [PageInfo]分页处理类
* @作者: ccx
* @日期: 2015-8-26 下午2:50:47
* <p>版权所有:(C)1998-2020 燕园夜雨</p>
* @param <E>
*/

public class PageInfo<E> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** rows 当前页的数据列表*/
	private List<E> rows;
	
	/** total 数据总条数*/
	private Integer total;
	
	/** pageSize 分页大小*/
	private Integer pageSize = 10;
	
	/** pageNumber 当前页码*/
	private Integer pageNum = 1;
	
	/** maxPageNum 最大页码*/
	private Integer maxPageNum = 0;
	
	/** prePage 上一页*/
	private Integer prePage = 1;
	
	/** nextPage 下一页*/
	private Integer nextPage = 1;
	
	/** start 开始条*/
	private Integer start=0;
	
	private int code;
	
	private int msg;

	public Integer getStart() {
		start = (getPageNum()-1) * getPageSize();
		if(start < 0) start=0;
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public List<E> getRows() {
		return rows;
	}
	public void setRows(List<E> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
		if(total%pageSize==0){
			maxPageNum = total/pageSize;
		}else {
			maxPageNum = total/pageSize + 1;
		}
		if(maxPageNum < 1) maxPageNum=1;
		if(pageNum > maxPageNum){
			pageNum = maxPageNum;
		}
	}

	/**
	 *获取
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 *设置
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 *获取
	 * @return the pageNumber
	 */
	public Integer getPageNum() {
		if(null == pageNum || pageNum < 1) pageNum=1;
		return pageNum;
	}

	/**
	 *设置
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getMaxPageNum() {
		return maxPageNum;
	}

	public void setMaxPageNum(Integer maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	
	public Integer getPrePage() {
		prePage=pageNum-1;
		if(prePage<1)prePage=1;
		return prePage;
	}
	public Integer getNextPage() {
		nextPage=pageNum+1;
		if(nextPage>getMaxPageNum())nextPage=maxPageNum;
		return nextPage;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getMsg() {
		return msg;
	}
	
	public void setMsg(int msg) {
		this.msg = msg;
	}
	
}
