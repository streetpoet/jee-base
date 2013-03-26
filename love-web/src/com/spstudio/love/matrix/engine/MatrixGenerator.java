package com.spstudio.love.matrix.engine;

import java.io.File;

import com.spstudio.love.matrix.engine.freemarker.FreemarkerGenerator;
import com.spstudio.love.matrix.engine.freemarker.MatrixFreeMarkerUtil;

public class MatrixGenerator {

	private ConfigBean cb;
	
	/*
	public static void main(String args[]){
		ConfigBean config = new ConfigBean();
		config.setTemplateInputPath("/Users/sp/Documents/work/git/jee-base/love-web/build");
		config.setOutputPath("/Users/sp/Documents/work/git/jee-base/love-web/build");
		MatrixModule mm = new MatrixModule();
		mm.setModuleName("project");
		config.setMatrixModule(mm);
		new MatrixGenerator().execute(config);
	}
	*/

	public void execute(ConfigBean cb) {
		this.cb = cb;
		refreshFileList(cb.getTemplateInputPath());
	}

	private void refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				processFolder(files[i]);
				refreshFileList(files[i].getAbsolutePath());
			} else {
				processFile(files[i]);
			}
		}
	}
	
	private void processFolder(File folder){
		String outputFolderPathWithFtlSymbol = cb.getOutputPath() + folder.getAbsolutePath().replace(cb.getTemplateInputPath().substring(0, cb.getTemplateInputPath().lastIndexOf(File.separator)), "");
		File outputFolderFile = new File(MatrixFreeMarkerUtil.convertFtlString(cb, outputFolderPathWithFtlSymbol));
		if (!outputFolderFile.exists()){
			if (!outputFolderFile.mkdirs()){
				System.err.println("can't create folder --> " + outputFolderPathWithFtlSymbol);
			}
		}
	}
	
	private void processFile(File file){
		FreemarkerGenerator fg = new FreemarkerGenerator(cb);
		fg.generate(file);
	}
}
