package com.spstudio.love.tbmarket.entity;

import java.io.Serializable;
import javax.enterprise.inject.Model;
import com.spstudio.love.tbmarket.qualifier.TbAcountBeanQualifier;

@Model
@TbAcountBeanQualifier
public class TbAcountBean implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -78565420607757L;
	
	private int id;
	
	public void clear(){
		id = 0;
	}
	
	public void setTbAcountBean(TbAcountBean tbAcountBean){
		id = tbAcountBean.id;
	}
	
	public TbAcountBean clone(){
		TbAcountBean tbAcountBean = null;
		try{
			tbAcountBean = (TbAcountBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return tbAcountBean;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
