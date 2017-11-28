package com.mine.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> {

	private Integer pageCurrent;//当前页码
	
	private Integer pageNum;//总页数
	
	private Integer pageCount;//总行数
	
	private Integer pageSize=10;//每页的行数
	
	private List<T> list;
	
	private Map<String, String> orderBy=new HashMap<String, String>();
	
	private T obj;//条件查询的对象

	public Integer getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Map<String, String> getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Map<String, String> orderBy) {
		this.orderBy = orderBy;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}
	
	
}
