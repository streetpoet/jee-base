package com.spstudio.love.matrix.engine.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.spstudio.love.matrix.engine.ConfigBean;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerGenerator {

	private ConfigBean cb;

	public FreemarkerGenerator(ConfigBean cb) {
		this.cb = cb;
	}

	public void generate(File file) {
		
		File templatefolder = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator)));
		String outputFilePathWithFtlSymbol = cb.getOutputPath() + file.getAbsolutePath().replace(cb.getTemplateInputPath().substring(0, cb.getTemplateInputPath().lastIndexOf(File.separator)), "");
		File outputFile = new File(MatrixUtil.convertFtlString(cb.getMatrixModule(), outputFilePathWithFtlSymbol).replace(".ftl", ""));
		
		try {
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(templatefolder);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template temp = cfg.getTemplate(file.getName());
			Map<Object, Object> root = new HashMap<Object, Object>();
			root.put(ConfigBean.MODULE_BEAN_VAR, MatrixUtil.convertBean(cb.getMatrixModule()));
			
			/*
			 * add customer method here.
			 */
			root.put(UID.UID, new UID());
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
			temp.process(root, bw);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
