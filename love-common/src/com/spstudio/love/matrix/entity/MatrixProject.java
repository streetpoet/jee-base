package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;

@Model
@MatrixProjectQualifier
public class MatrixProject implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -174859695150398L;
	
	private int id = -1;
	private String projectName; // project label
	private String projectCode; // english code, generated to whole project. like 'love-web'
	private String packageString; // like 'com.nsv.tb2013a'
	
	public void clear(){
		id = -1;
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

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getPackageString() {
		return packageString;
	}

	public void setPackageString(String packageString) {
		this.packageString = packageString;
	}
	
}
