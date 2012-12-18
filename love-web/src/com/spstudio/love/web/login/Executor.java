package com.spstudio.love.web.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.StandardNavigation;

@RequestScoped
@ManagedBean
@Named
public class Executor {
	
	private Logger logger = Logger.getLogger(Executor.class);
	
	@Inject
	private Greeting greeting;
	
	public Object doExecute(){
		logger.trace("### doExecute");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println(greeting.greet("william"));
		return StandardNavigation.SUCCESS;
	}
	
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
