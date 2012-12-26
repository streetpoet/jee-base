package com.spstudio.love.web.system;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.ProjectStage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

@ApplicationScoped
@javax.faces.bean.ApplicationScoped
@ManagedBean(eager = true)
@Named
public class LoveDaemon implements Serializable{
	
	private static final long serialVersionUID = 432101143011064475L;

	private Logger log = Logger.getLogger(LoveDaemon.class);
	
	@Resource(name = "LoveWebDB",
			mappedName = "java:/LoveWebDB",
			authenticationType = AuthenticationType.CONTAINER, 
			shareable = false, 
			description = "datasource of connection pool")
	private static javax.sql.DataSource DATASOURCE = null;
	
	private int totalUserCount = 0;
	
	public LoveDaemon(){
		log.info("LoveDaemon started.");
		
		/*
		 * check project status
		 */
		javax.faces.application.Application application = FacesContext.getCurrentInstance().getApplication();
		if (application.getProjectStage() == ProjectStage.Development){
			log.warn("Project is in development stage.");
		}
	}
	
	public static DataSource getDataSource(){
		return DATASOURCE;
	}
	
	public void printUserCount(){
		totalUserCount ++;
		System.out.println("Count = " + totalUserCount);
	}
}
