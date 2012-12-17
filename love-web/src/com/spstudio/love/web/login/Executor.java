package com.spstudio.love.web.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.DatabaseHelper;
import com.spstudio.love.web.common.StandardNavigation;

@RequestScoped
@ManagedBean
public class Executor {
	
	private Logger logger = Logger.getLogger(Executor.class);
	
	public Object doExecute(){
		logger.trace("### doExecute");
		new DatabaseHelper().doQuery("select * from sp");
		return StandardNavigation.SUCCESS;
	}
	
}
