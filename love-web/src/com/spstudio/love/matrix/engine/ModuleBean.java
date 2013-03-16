package com.spstudio.love.matrix.engine;

public class ModuleBean {
	
	private String moduleName;
	private String firstUpperModuleName;
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getFirstUpperModuleName() {
		return moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1);
	}
	public void setFirstUpperModuleName(String firstUpperModuleName) {
		this.firstUpperModuleName = firstUpperModuleName;
	}
}
