package ${project.packageString}.system.bean;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
public class PageObject implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	private int currentPageNumber;
	private int maxPageNumber;
	private long totalRecordsNumber;
	private int offset;
	private int maxRecordsPerPage = 6;

	public PageObject clone(){
		PageObject p = null;
		try{
			p = (PageObject)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return p;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getMaxPageNumber() {
		return maxPageNumber;
	}

	public void setMaxPageNumber(int maxPageNumber) {
		this.maxPageNumber = maxPageNumber;
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

	public int getMaxRecordsPerPage() {
		return maxRecordsPerPage;
	}

	public void setMaxRecordsPerPage(int maxRecordsPerPage) {
		this.maxRecordsPerPage = maxRecordsPerPage;
	}

	

}
