package com.spstudio.love.interest.event;

import interfaces.interest.IInterest;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.qualifier.TechSelectBeanQualifier;
import com.spstudio.love.interest.qualifier.InterestRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class InterestCreateEventHandler implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -145521859565844L;
	
	@Inject @TechSelectBeanQualifier TechSelectBean interestProject;
	@Inject @InterestRemoteBean IInterest interestRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void createTechSelectBean(@Observes @InterestCreateEventQualifier InterestCreateEvent event){
		
		TechSelectBean tempTechSelectBean = interestProject.clone();
		boolean result = interestRemoteBean.createTechSelectBean(tempTechSelectBean);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		interestProject.clear();
	}
}
