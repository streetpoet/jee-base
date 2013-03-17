package com.spstudio.love.${module.moduleName}.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.${module.moduleName}.qualifier.${module.entityBeanName?cap_first}Qualifier
public class ${module.entityBeanName?cap_first} implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	private int id;
	
	public void clear(){
		id = 0;
	}
	
	public void set${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}){
		id = ${module.entityBeanName}.id;
	}
	
	public ${module.entityBeanName?cap_first} clone(){
		${module.entityBeanName?cap_first} ${module.entityBeanName} = null;
		try{
			${module.entityBeanName} = (${module.entityBeanName?cap_first})super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return ${module.entityBeanName};	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
