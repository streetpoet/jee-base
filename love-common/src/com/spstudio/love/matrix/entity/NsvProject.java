package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.matrix.qualifier.NsvProjectQualifier
public class NsvProject implements Cloneable, Serializable{
	
	private int id;
	
	public void clear(){
		id = 0;
	}
	
	public void setNsvProject(NsvProject nsvProject){
		id = nsvProject.id;
	}
	
	public NsvProject clone(){
		NsvProject nsvProject = null;
		try{
			nsvProject = (NsvProject)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return nsvProject;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
