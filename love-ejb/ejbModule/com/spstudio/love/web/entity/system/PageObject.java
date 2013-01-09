package com.spstudio.love.web.entity.system;

import java.io.Serializable;

public class PageObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5681846021601088862L;
	
	private int totalRecords;
	private int offset;
	private int maxRowNumber;

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getMaxRowNumber() {
		return maxRowNumber;
	}

	public void setMaxRowNumber(int maxRowNumber) {
		this.maxRowNumber = maxRowNumber;
	}
}
