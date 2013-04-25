package com.spstudio.love.interest.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
public class ChartClassifyPercentageBean implements Cloneable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7919474439719365021L;
	
	private String classifyName;
	private long selectedUserCount;

	public ChartClassifyPercentageBean clone(){
		ChartClassifyPercentageBean chartClassifyPercentageBean = null;
		try{
			chartClassifyPercentageBean = (ChartClassifyPercentageBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return chartClassifyPercentageBean;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public long getSelectedUserCount() {
		return selectedUserCount;
	}

	public void setSelectedUserCount(long selectedUserCount) {
		this.selectedUserCount = selectedUserCount;
	}
}
