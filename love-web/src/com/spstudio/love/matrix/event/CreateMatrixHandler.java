package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.engine.Client;
import com.spstudio.love.matrix.engine.ConfigBean;
import com.spstudio.love.matrix.engine.ModuleBean;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class CreateMatrixHandler implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -45222395318112L;
	
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;
	@Inject @MatrixRemoteBean IMatrix matrixRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject @com.spstudio.love.matrix.qualifier.MatrixModuleQualifier MatrixModule module;
	@Inject @LoveLogged Logger log;

	@LoveTrace
	public void createMatrixProject(@Observes @CreateMatrixEventQualifier CreateMatrixEvent event){
		log.info(module.getEntityBeanName());
		log.info(module.getModuleName());
		log.info(module.getSingletonEjbMethodName());
		log.info(module.getId());
		
		ConfigBean cb = new ConfigBean();
		cb.setTemplateInputPath("/Users/sp/Documents/work/git/jee-base/love-web/matrix-files/love-web");
		cb.setOutputPath("/Users/sp/Documents/work/git/jee-base/love-web/matrix-files/output");
		ModuleBean mb = new ModuleBean();
		mb.setModuleName(module.getModuleName());
		mb.setEntityBeanName(module.getEntityBeanName());
		mb.setSingletonEjbMethodName(module.getSingletonEjbMethodName());
		cb.setModuleBean(mb);
		
		new Client().execute(cb);
		
		downloadFile("/Users/sp/Documents/work/git/jee-base/love-web/matrix-files/output", "love-web.zip");
		
	}
	
	public void downloadFile(String path, String fileName) {  
        try {  
            // 获得ServletContext对象  
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
            // 取得文件的绝对路径  
//            String realName = servletContext.getRealPath(path) + File.separator + fileName;  
            String realName = path + File.separator + fileName;
            HttpServletResponse response = (HttpServletResponse) FacesContext  
                    .getCurrentInstance().getExternalContext().getResponse();  
            downloadFile(response, realName, fileName);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        FacesContext.getCurrentInstance().responseComplete();  
    }  
	
	public static void downloadFile(HttpServletResponse response,  
            String realName, String fileName) throws IOException {  
        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");  
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
        response.setContentType("application/zip");
        ServletOutputStream servletOutputStream = response.getOutputStream();  
        byte[] b = new byte[1024];  
        int i = 0;  
        FileInputStream fis = new java.io.FileInputStream(realName);  
        while ((i = fis.read(b)) > 0) {  
            servletOutputStream.write(b, 0, i);  
        }  
        servletOutputStream.flush();  
        servletOutputStream.close();  
    } 
}
