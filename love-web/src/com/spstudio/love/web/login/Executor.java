package com.spstudio.love.web.login;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.spstudio.love.web.common.StandardNavigation;
import com.spstudio.love.web.qualifiers.LoveLogged;

@javax.enterprise.context.RequestScoped
@Named
public class Executor {
	
	public Object doExecute(){
		return StandardNavigation.SUCCESS;
	}
	
	@LoveLogged
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
