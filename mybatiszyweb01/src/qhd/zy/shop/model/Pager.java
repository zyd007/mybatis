package qhd.zy.shop.model;

import java.util.List;

public class Pager<E> {
	//第几页
	private int IndexPage;
	//每页首地址
	private int offset;
	//一页多少记录
	private int pageRecord;
	//总共多少页
	private int totalPage;
	//总共多少记录
	private int totalRecord;
	//放的得到的数据
	private List<E>datas;
	public int getIndexPage() {
		return IndexPage;
	}
	public void setIndexPage(int indexPage) {
		IndexPage = indexPage;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<E> getDatas() {
		return datas;
	}
	public void setDatas(List<E> datas) {
		this.datas = datas;
	}
	
}
