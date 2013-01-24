package com.spstudio.love.system.action;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;

import com.spstudio.love.system.nav.SystemNav;

@Model
public class SysCore {
	public Object logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return SystemNav.LOGOUT;
	}
}
