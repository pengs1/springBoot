package com.pss.domain;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> contents;
	private int pageSize;
	private int totalSize;
	private int curPage;
	
	public Page(int curPage, int totalSize, int pageSize, List<T> contents) {
		this.curPage = curPage;
		this.totalSize = totalSize;
		this.pageSize = pageSize;
		this.contents = contents;
	}
	
	public List<T> getContents() {
		return contents;
	}
	
	public void setContents(List<T> contents) {
		this.contents = contents;
	}
	
	public int getTotalPages() {
		return totalSize / pageSize + 1;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurPage() {
		return curPage;
	}
	
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
}