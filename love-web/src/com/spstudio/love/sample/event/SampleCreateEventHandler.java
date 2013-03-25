package com.spstudio.love.sample.event;

import interfaces.sample.ISample;

import java.io.Serializable;

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
public class SampleCreateEventHandler implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -142160740745558L;
	
	@Inject @EntityQualifier Entity sampleProject;
	@Inject @SampleRemoteBean ISample sampleRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void createEntity(@Observes @SampleCreateEventQualifier SampleCreateEvent event){
		
		Entity tempEntity = sampleProject.clone();
		boolean result = sampleRemoteBean.createEntity(tempEntity);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		sampleProject.clear();
	}
}
