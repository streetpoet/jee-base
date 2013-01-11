package com.spstudio.love.system.bean;

import java.io.Serializable;

import javax.inject.Named;

@Named
public class PageObject implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5681846021601088862L;
	
	private int currentPageNo;
	private long totalRecordsNumber;
	private int offset;
	private int numberPerPage = 6;

	public PageObject clone(){
		PageObject p = null;
		try{
			p = (PageObject)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return p;
	}

	public long getTotalRecordsNumber() {
		return totalRecordsNumber;
	}

	public void setTotalRecordsNumber(long totalRecordsNumber) {
		this.totalRecordsNumber = totalRecordsNumber;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getNumberPerPage() {
		return numberPerPage;
	}

	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}

}
