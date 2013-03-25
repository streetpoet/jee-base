package com.spstudio.love.sample.event;

import interfaces.sample.ISample;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.sample.entity.Entity;
import com.spstudio.love.sample.qualifier.EntityQualifier;
import com.spstudio.love.sample.qualifier.SampleRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class SampleUpdateEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -281349793482011L;
	
	@Inject @EntityQualifier Entity entity;
	@Inject @SampleRemoteBean ISample sampleRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void updateEntity(@Observes @SampleUpdateEventQualifier SampleUpdateEvent event){
		
		int result = sampleRemoteBean.updateEntity(entity.clone());
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages.sample.Message", context.getViewRoot().getLocale());
		switch (result) {
		case 1:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("sample.modify.msg.success"), ""));
			break;

		default:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("sample.modify.msg.failure"), ""));
			break;
		}
	}
}
