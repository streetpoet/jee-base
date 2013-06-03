package com.spstudio.love.tbmarket.event;

import interfaces.tbmarket.ITbmarket;

import java.io.Serializable;

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
public class TbmarketCreateEventHandler implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -221465596515804L;
	
	@Inject @TbAcountBeanQualifier TbAcountBean tbAcountBean;
	@Inject @TbmarketRemoteBean ITbmarket tbmarketRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void createTbAcountBean(@Observes @TbmarketCreateEventQualifier TbmarketCreateEvent event){
		
		TbAcountBean tempTbAcountBean = tbAcountBean.clone();
		boolean result = tbmarketRemoteBean.createTbAcountBean(tempTbAcountBean);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		tbAcountBean.clear();
	}
}
