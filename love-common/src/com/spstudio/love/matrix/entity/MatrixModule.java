package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

import com.spstudio.love.matrix.qualifier.MatrixModuleQualifier;

@Model
@MatrixModuleQualifier
public class MatrixModule implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -278460101699583L;
	
	private int id;
	private String moduleName; /** 模块名称，代表项目中的模块，模块中有若干的功能。小写字母开头。 */
	private String entityBeanName; /** 可预见的在EJB与WEB SERVER间传递的实体对象，默认对于普通Session EJB，会生成增删改查方法。 */
	private String singletonEjbMethodName; /** 单例EJB方法名，用来生成demo代码 */
	private String functionName; /** 模块中的子功能名，子功能可能是一个模块中的一个画面。 */
	
	public void clear(){
		id = 0;
	}
	
	public void setMatrixModule(MatrixModule matrixModule){
		id = matrixModule.id;
		moduleName = matrixModule.moduleName;
		entityBeanName = matrixModule.entityBeanName;
		singletonEjbMethodName = matrixModule.singletonEjbMethodName;
		functionName = matrixModule.functionName;
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

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
