package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.matrix.qualifier.MatrixProjectQualifier
public class MatrixProject implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -174859695150398L;
	
	private int id;
	private String projectName;
	
	public void clear(){
		id = 0;
	}
	
	public void setMatrixProject(MatrixProject matrixProject){
		id = matrixProject.id;
		projectName = matrixProject.projectName;
	}
	
	public MatrixProject clone(){
		MatrixProject matrixProject = null;
		try{
			matrixProject = (MatrixProject)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return matrixProject;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
