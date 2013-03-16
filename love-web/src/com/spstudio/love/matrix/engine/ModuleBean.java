package com.spstudio.love.matrix.engine;

public class ModuleBean {
	
	/*
	 * module properties
	 */
	private String moduleName;
	
	/*
	 * web properties
	 */
	
	
	/*
	 * ejb properties
	 */
	private String singletonEjbMethodName;
	
	/*
	 * common, for communicate from web to ejb
	 */
	private String entityBeanName;
	
	/*
	 * Getter/Setter
	 */
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
