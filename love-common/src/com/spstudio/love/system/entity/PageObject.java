package com.spstudio.love.system.entity;

import java.io.Serializable;

public class PageObject implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5681846021601088862L;
	
	private long totalRecordsNumber;
	private int offset;
	private int recordCountPerFetch;

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

	public int getRecordCountPerFetch() {
		return recordCountPerFetch;
	}

	public void setRecordCountPerFetch(int recordCountPerFetch) {
		this.recordCountPerFetch = recordCountPerFetch;
	}

}
