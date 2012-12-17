package com.spstudio.love.web.system;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.faces.application.Application;
import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

@ApplicationScoped
@ManagedBean(eager = true)
public class LoveDaemon {
	
	private Logger log = Logger.getLogger(LoveDaemon.class);
	
	@Resource(name = "LoveWebDB",
			mappedName = "java:/LoveWebDB",
			authenticationType = AuthenticationType.CONTAINER, 
			shareable = false, 
			description = "datasource of connection pool")
	private static javax.sql.DataSource DATASOURCE = null;
	
	
	public LoveDaemon(){
		log.info("LoveDaemon started.");
		
		/*
		 * check project status
		 */
		Application application = FacesContext.getCurrentInstance().getApplication();
		if (application.getProjectStage() == ProjectStage.Development){
			log.warn("Project is in development stage.");
		}
	}
	
	public static DataSource getDataSource(){
		return DATASOURCE;
	}
}
