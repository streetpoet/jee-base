package com.spstudio.love.${module.moduleName}.entity;

import java.io.Serializable;
import javax.enterprise.inject.Model;

@Model
public class ${module.selectBeanName?cap_first} implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	private int id = -1;
	private String label;
	
	public void clear(){
		id = -1;
		label = "";
	}
	
	public void set${module.selectBeanName?cap_first}(${module.selectBeanName?cap_first} ${module.selectBeanName}){
		id = ${module.selectBeanName}.id;
		label = ${module.selectBeanName}.label;
	}
	
	public ${module.selectBeanName?cap_first} clone(){
		${module.selectBeanName?cap_first} ${module.selectBeanName} = null;
		try{
			${module.selectBeanName} = (${module.selectBeanName?cap_first})super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return ${module.selectBeanName};	
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
