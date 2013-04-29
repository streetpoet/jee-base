package com.spstudio.love.matrix.engine;

import java.io.File;
import java.io.IOException;

import com.spstudio.love.matrix.engine.freemarker.FreemarkerGenerator;
import com.spstudio.love.matrix.engine.freemarker.MatrixFreeMarkerUtil;
import com.spstudio.love.system.tool.FileUtils;
import com.spstudio.love.system.tool.StringUtils;

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
		String destFolderString = MatrixFreeMarkerUtil.convertFtlString(cb, outputFolderPathWithFtlSymbol);
		if (destFolderString.contains(".")){
			for (String part: destFolderString.split(File.separator)){
				if (part.indexOf(".") > 0 && !destFolderString.contains(".settings")){
					String newPart = part.replaceAll("\\.", File.separator);
					destFolderString = destFolderString.replaceAll(part, newPart);
				}
			}
			createFolder(destFolderString);
		}else{
			createFolder(destFolderString);
		}
	}
	
	private void createFolder(String path){
		File outputFolderFile = new File(path);
		if (!outputFolderFile.exists()){
			if (!outputFolderFile.mkdirs()){
				System.err.println("can't create folder --> " + path);
			}
		}
	}
	
	private void processFile(File file){
		if (file.getName().contains(".ftl")){
			FreemarkerGenerator fg = new FreemarkerGenerator(cb);
			fg.generate(file);
		}else{
			// just copy file
			String outputFileStringWithSymbol = cb.getOutputPath() + file.getAbsolutePath().replace(cb.getTemplateInputPath().substring(0, cb.getTemplateInputPath().lastIndexOf(File.separator)), "");
			String convertedString = StringUtils.convertFilePath(MatrixFreeMarkerUtil.convertFtlString(cb, outputFileStringWithSymbol));
			try{
				FileUtils.copyFile(file, new File(convertedString));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
