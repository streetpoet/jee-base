package com.spstudio.love.interest.entity;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;

@Model
public class ClassifyStatBean implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2944108311376086114L;
	
	private int id;
	private int techClassifyId;
	private String techClassifyName;
	private List<String> selectedUserNameList;
	
	public void clear(){
		id = 0;
	}
	
	public void setTechSelectBean(ClassifyStatBean TechSelectBean){
		id = TechSelectBean.id;
	}
	
	public ClassifyStatBean clone(){
		ClassifyStatBean classifyStatBean = null;
		try{
			classifyStatBean = (ClassifyStatBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return classifyStatBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTechClassifyId() {
		return techClassifyId;
	}

	public void setTechClassifyId(int techClassifyId) {
		this.techClassifyId = techClassifyId;
	}

	public String getTechClassifyName() {
		return techClassifyName;
	}

	public void setTechClassifyName(String techClassifyName) {
		this.techClassifyName = techClassifyName;
	}

	public List<String> getSelectedUserNameList() {
		return selectedUserNameList;
	}

	public void setSelectedUserNameList(List<String> selectedUserNameList) {
		this.selectedUserNameList = selectedUserNameList;
	}
	
}
