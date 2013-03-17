package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
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

	@LoveTrace
	public void createMatrixProject(@Observes @CreateMatrixEventQualifier CreateMatrixEvent event){
		
		MatrixProject tempMatrixProject = matrixProject.clone();
		boolean result = matrixRemoteBean.createMatrixProject(tempMatrixProject);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		matrixProject.clear();
	}
}
