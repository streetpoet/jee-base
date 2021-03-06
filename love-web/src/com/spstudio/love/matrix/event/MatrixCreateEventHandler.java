package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;
import interfaces.matrix.IMatrixSingleton;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.spstudio.love.matrix.action.MatrixAction;
import com.spstudio.love.matrix.engine.ConfigBean;
import com.spstudio.love.matrix.engine.MatrixGenerator;
import com.spstudio.love.matrix.entity.MatrixFunction;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.qualifier.MatrixModuleQualifier;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.tool.ZipUtils;

@Dependent
public class MatrixCreateEventHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -226489381813885L;

	@Inject @MatrixRemoteBean IMatrix matrixRemoteBean;
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingletonBean;
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;
	@Inject @MatrixModuleQualifier MatrixModule matrixModule;
	@Inject MatrixFunction matrixFunction;
	@Inject MatrixAction matrixAction;

	@LoveTrace
	public void createMatrixProject(@Observes @MatrixCreateEventQualifier MatrixCreateEvent event) {
		switch (event.getMode()) {
		case SOLUTION:
			persistance();
			generateSolution();
			break;
		case PROJECT:
			if (matrixRemoteBean.createMatrixProject(matrixProject.clone())){
				matrixSingletonBean.refreshProjectCache();
			}
			break;
		case MODULE:
			matrixProject.setId(matrixAction.getSelectedProjectId());
			matrixRemoteBean.createMatrixModule(matrixProject.clone(), matrixModule.clone());
			break;
		case FUNCTION:
			matrixModule.setId(matrixAction.getSelectedModuleId());
			matrixRemoteBean.createMatrixFunction(matrixModule.clone(), matrixFunction.clone());
			break;
		default:
			break;
		}
	}
	
	private void persistance(){
		matrixProject.setId(matrixAction.getSelectedProjectId());
		matrixModule.setId(matrixAction.getSelectedModuleId());
		matrixFunction.setId(matrixAction.getSelectedFunctionId());
		matrixRemoteBean.updateSolution(matrixProject.clone(), matrixModule.clone(), matrixFunction.clone());
	}
	
	private void generateSolution(){
		MatrixGenerator generator = new MatrixGenerator();
		ConfigBean cb = new ConfigBean();
		Map<String, List<String>> configMap = matrixSingletonBean.retrieveGenerationConfiguration();

		cb.setOutputPath(configMap.get(ConfigBean.OUTPUT_PATH).get(0));
		cb.setMatrixModule(matrixModule);
		cb.setMatrixFunction(matrixFunction);
		cb.setMatrixProject(matrixProject);

		List<String> listInputPath = configMap.get(ConfigBean.INPUT_FTL_PATH);
		try{
			deletefile(cb.getOutputPath());
		}catch(Exception e){
			e.printStackTrace();
		}
		for (String inputPath : listInputPath) {
			cb.setTemplateInputPath(inputPath);
			generator.execute(cb);
		}

		try {
			new ZipUtils().compress(cb.getOutputPath());
			File outputFolder = new File(cb.getOutputPath());
			downloadFile(outputFolder.getParent() + File.separator + outputFolder.getName() + ".zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean deletefile(String delpath) throws Exception {
		File file = new File(delpath);
		if (!file.isDirectory()) {
			file.delete();
		} else if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File delfile = new File(delpath + File.separator + filelist[i]);
				if (!delfile.isDirectory()) {
					delfile.delete();
				} else if (delfile.isDirectory()) {
					deletefile(delpath + File.separator + filelist[i]);
				}
			}
			file.delete();
		}
		return true;
	}

	private void downloadFile(String zipFile) {
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		try {
			File file = new File(zipFile);
			if (file.exists()) {
				ServletOutputStream outstream = response.getOutputStream();
				FileInputStream fis = new FileInputStream(file);

				response.setContentType("application/zip");
				response.setHeader("Transfer-Encoding", "chunked");
				response.addHeader("Content-disposition", "attachment;filename=" + file.getName());
				BufferedInputStream bufInStrm = new BufferedInputStream(fis);

				int readBytes = 0;
				int bufferSize = 8192;
				byte[] buffer = new byte[bufferSize];
				while ((readBytes = bufInStrm.read(buffer)) != -1) {
					if (readBytes == bufferSize) {
						outstream.write(buffer);
					} else {
						outstream.write(buffer, 0, readBytes);
					}
					outstream.flush();
					response.flushBuffer();
				}

				fis.close();
				outstream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FacesContext.getCurrentInstance().responseComplete();
		}
	}

}
