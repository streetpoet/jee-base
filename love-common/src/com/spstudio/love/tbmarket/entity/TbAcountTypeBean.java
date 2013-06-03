package com.spstudio.love.tbmarket.entity;

import java.io.Serializable;
import javax.enterprise.inject.Model;

@Model
public class TbAcountTypeBean implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -180604046871524L;
	
	private int id = -1;
	private String label;
	
	public void clear(){
		id = -1;
		label = "";
	}
	
	public void setTbAcountTypeBean(TbAcountTypeBean tbAcountTypeBean){
		id = tbAcountTypeBean.id;
		label = tbAcountTypeBean.label;
	}
	
	public TbAcountTypeBean clone(){
		TbAcountTypeBean tbAcountTypeBean = null;
		try{
			tbAcountTypeBean = (TbAcountTypeBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return tbAcountTypeBean;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
