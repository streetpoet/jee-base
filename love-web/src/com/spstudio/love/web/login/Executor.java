package com.spstudio.love.web.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.StandardNavigation;

@RequestScoped
@ManagedBean
public class Executor {
	
	private Logger logger = Logger.getLogger(Executor.class);
	
	public Object doExecute(){
		logger.trace("### doExecute");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return StandardNavigation.SUCCESS;
	}
	
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
