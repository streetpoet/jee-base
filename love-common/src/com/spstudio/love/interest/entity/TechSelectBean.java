package com.spstudio.love.interest.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import com.spstudio.love.interest.qualifier.TechSelectBeanQualifier;

@Model
@TechSelectBeanQualifier
public class TechSelectBean implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -48654950888263L;
	
	private int id;
	private int userId;
	private int techClassifyId;
	
	public void clear(){
		id = 0;
	}
	
	public void setTechSelectBean(TechSelectBean TechSelectBean){
		id = TechSelectBean.id;
	}
	
	public TechSelectBean clone(){
		TechSelectBean TechSelectBean = null;
		try{
			TechSelectBean = (TechSelectBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return TechSelectBean;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTechClassifyId() {
		return techClassifyId;
	}

	public void setTechClassifyId(int techClassifyId) {
		this.techClassifyId = techClassifyId;
	}
}
