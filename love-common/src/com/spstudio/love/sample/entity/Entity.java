package com.spstudio.love.sample.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.sample.qualifier.EntityQualifier
public class Entity implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -154669473291546L;
	
	private int id;
	
	public void clear(){
		id = 0;
	}
	
	public void setEntity(Entity entity){
		id = entity.id;
	}
	
	public Entity clone(){
		Entity entity = null;
		try{
			entity = (Entity)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return entity;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
