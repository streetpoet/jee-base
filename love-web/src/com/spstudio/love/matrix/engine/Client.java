package com.spstudio.love.matrix.engine;

import java.io.File;

public class Client {

	private ConfigBean cb;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigBean cb = new ConfigBean();
		cb.setTemplateInputPath("/Users/sp/Documents/work/git/jee-base/love-web/matrix-files/love-ejb");
		cb.setOutputPath("/Users/sp/Documents/work/git/jee-base/love-web/matrix-files/output");
		ModuleBean mb = new ModuleBean();
		mb.setModuleName("matrix");
		cb.setModuleBean(mb);
		
		new Client().execute(cb);
	}	

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
		File outputFolderFile = new File(MatrixUtil.convertFtlString(cb.getModuleBean(), outputFolderPathWithFtlSymbol));
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
