package com.spstudio.love.tbmarket.event;

import interfaces.tbmarket.ITbmarket;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.tbmarket.entity.TbAcountBean;
import com.spstudio.love.tbmarket.qualifier.TbAcountBeanQualifier;
import com.spstudio.love.tbmarket.qualifier.TbmarketRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class TbmarketDeleteEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -220089600229989L;
	
	@Inject @TbAcountBeanQualifier TbAcountBean tbAcountBean;
	@Inject @TbmarketRemoteBean ITbmarket tbmarketRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void deleteTbAcountBean(@Observes @TbmarketDeleteEventQualifier TbmarketDeleteEvent event){
		
		int result = tbmarketRemoteBean.deleteTbAcountBean(tbAcountBean.clone());
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages.tbmarket.Message", context.getViewRoot().getLocale());
		switch (result) {
		case 1:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("tbmarket.modify.msg.success"), ""));
			break;

		default:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("tbmarket.modify.msg.failure"), ""));
			break;
		}
	}
}
