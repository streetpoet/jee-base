package com.spstudio.love.interest.event;

import interfaces.interest.IInterest;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.qualifier.InterestRemoteBean;
import com.spstudio.love.interest.qualifier.TechSelectBeanQualifier;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class InterestUpdateEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -11770190279903L;
	
	@Inject @TechSelectBeanQualifier TechSelectBean techSelectBean;
	@Inject @InterestRemoteBean IInterest interestRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void updateTechSelectBean(@Observes @InterestUpdateEventQualifier InterestUpdateEvent event){
		
		int result = interestRemoteBean.updateTechSelectBean(techSelectBean.clone());
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages.interest.Message", context.getViewRoot().getLocale());
		switch (result) {
		case 1:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("interest.modify.msg.success"), ""));
			break;

		default:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("interest.modify.msg.failure"), ""));
			break;
		}
	}
}
