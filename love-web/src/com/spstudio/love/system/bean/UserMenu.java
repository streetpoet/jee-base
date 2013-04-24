package com.spstudio.love.system.bean;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class UserMenu implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 435172976259896255L;
	
	private Object currentMenuNav;

	public Object getCurrentMenuNav() {
		return currentMenuNav;
	}

	public void setCurrentMenuNav(Object currentMenuNav) {
		this.currentMenuNav = currentMenuNav;
	}

}
