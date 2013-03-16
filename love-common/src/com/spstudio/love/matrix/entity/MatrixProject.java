package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;

@Model
@MatrixProjectQualifier
public class MatrixProject implements Cloneable, Serializable{
	
	private int id;
	
	public void clear(){
		id = 0;
	}
	
	public void setMatrixProject(MatrixProject p){
		id = p.id;
	}
	
	public MatrixProject clone(){
		MatrixProject p = null;
		try{
			p = (MatrixProject)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return p;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
