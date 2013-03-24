package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;
import interfaces.matrix.IMatrixSingleton;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.spstudio.love.matrix.engine.ConfigBean;
import com.spstudio.love.matrix.engine.MatrixGenerator;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.qualifier.MatrixModuleQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.qualifier.LoveTrace;

@Dependent
public class MatrixCreateEventHandler implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -226489381813885L;
	
	@Inject @MatrixRemoteBean IMatrix matrixRemoteBean;
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingletonBean;
	@Inject @MatrixModuleQualifier MatrixModule matrixModule;

	@LoveTrace
	public void createMatrixProject(@Observes @MatrixCreateEventQualifier MatrixCreateEvent event){
		
		MatrixGenerator generator = new MatrixGenerator();
		ConfigBean cb = new ConfigBean();
		Map<String, List<String>> configMap = matrixSingletonBean.retrieveGenerationConfiguration();
		
		cb.setOutputPath(configMap.get(ConfigBean.OUTPUT_PATH).get(0));
		cb.setMatrixModule(matrixModule);
		
		List<String> listInputPath = configMap.get(ConfigBean.INPUT_FTL_PATH);
		for (String inputPath: listInputPath){
			cb.setTemplateInputPath(inputPath);
			generator.execute(cb);
		}
		
		try{
			downloadZipFile(cb.getOutputPath());
		}catch(Exception e){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getLocalizedMessage(), ""));
		}
	}
	
	private void downloadZipFile(String path) throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		HttpServletResponse httpServletResponse = (HttpServletResponse) context.getExternalContext().getResponse();
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();

		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		InputStream is = new FileInputStream(path);
		ZipEntry entry = new ZipEntry(path);
		ZipOutputStream zos = new ZipOutputStream(byteOutputStream);
		zos.putNextEntry(entry);
		byte[] buffer = new byte[1024];
		int i = -1;
		while ((i = is.read(buffer)) != -1) {
			zos.write(buffer, 0, i);
		}
		is.close();
		zos.close();

		String fileminitype = "application/x-download";
		httpServletResponse.setContentType(fileminitype);
		httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "solution" + ".zip");
		httpServletResponse.setContentLength(byteOutputStream.size());
		byteOutputStream.write(byteOutputStream.toByteArray());
		byteOutputStream.close();
		outputStream.close();
		context.responseComplete();
	}
}
