package com.spstudio.love.web.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.StandardNavigation;
import com.spstudio.love.web.qualifiers.LoadFamilyInterceptor;

@RequestScoped
@ManagedBean
@Named
public class Executor {
	
	private Logger logger = Logger.getLogger(Executor.class);
	
	@LoadFamilyInterceptor
	public Object doExecute(){
		logger.trace("### doExecute");
		return StandardNavigation.SUCCESS;
	}
	
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
