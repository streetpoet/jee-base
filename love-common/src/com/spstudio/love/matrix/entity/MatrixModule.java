package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.matrix.qualifier.MatrixModuleQualifier
public class MatrixModule implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -278460101699583L;
	
	private int id;
	private String moduleName;
	private String entityBeanName;
	private String singletonEjbMethodName;
	
	public void clear(){
		id = 0;
	}
	
	public void setMatrixModule(MatrixModule matrixModule){
		id = matrixModule.id;
	}
	
	public MatrixModule clone(){
		MatrixModule matrixModule = null;
		try{
			matrixModule = (MatrixModule)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return matrixModule;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEntityBeanName() {
		return entityBeanName;
	}

	public void setEntityBeanName(String entityBeanName) {
		this.entityBeanName = entityBeanName;
	}

	public String getSingletonEjbMethodName() {
		return singletonEjbMethodName;
	}

	public void setSingletonEjbMethodName(String singletonEjbMethodName) {
		this.singletonEjbMethodName = singletonEjbMethodName;
	}
	
}