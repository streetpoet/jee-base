package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;

import java.io.Serializable;
import java.util.ResourceBundle;

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
public class DeleteMatrixHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -62854043256098L;
	
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;
	@Inject @MatrixRemoteBean IMatrix matrixRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void deleteMatrixProject(@Observes @DeleteMatrixEventQualifier DeleteMatrixEvent event){
		
		int result = matrixRemoteBean.deleteMatrixProject(matrixProject.clone());
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages.matrix.Message", context.getViewRoot().getLocale());
		switch (result) {
		case 1:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("matrix.modify.msg.success"), ""));
			break;

		default:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("matrix.modify.msg.failure"), ""));
			break;
		}
	}
}
