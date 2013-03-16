package com.spstudio.love.matrix.engine;

public class ConfigBean {
	
	public static final String MODULE_BEAN_VAR = "module";
	
	private String templateInputPath;
	private String outputPath;
	private ModuleBean moduleBean;
	
	public String getTemplateInputPath() {
		return templateInputPath;
	}
	public void setTemplateInputPath(String templateInputPath) {
		this.templateInputPath = templateInputPath;
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	public ModuleBean getModuleBean() {
		return moduleBean;
	}
	public void setModuleBean(ModuleBean moduleBean) {
		this.moduleBean = moduleBean;
	}
	
	
}
