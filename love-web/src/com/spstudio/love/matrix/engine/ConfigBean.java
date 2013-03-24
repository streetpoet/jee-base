package com.spstudio.love.matrix.engine;

import javax.enterprise.context.RequestScoped;

import com.spstudio.love.matrix.entity.MatrixModule;

@RequestScoped
public class ConfigBean {
	
	public static final String MODULE_BEAN_VAR = "module";
	public static final String INPUT_FTL_PATH = "INPUT_FTL_PATH";
	public static final String OUTPUT_PATH = "OUTPUT_PATH";
	
	private String templateInputPath;
	private String outputPath;
	private MatrixModule matrixModule;
	
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
	public MatrixModule getMatrixModule() {
		return matrixModule;
	}
	public void setMatrixModule(MatrixModule matrixModule) {
		this.matrixModule = matrixModule;
	}
}
