package com.spstudio.love.web.system;

import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

@ApplicationScoped
@ManagedBean(eager = true)
public class LoveDaemon{

	private Logger log = Logger.getLogger(LoveDaemon.class);
	public static InitialContext ic;
	
	public LoveDaemon(){
		log.info("LoveDaemon started.");
		
		/*
		 * check project status
		 */
		javax.faces.application.Application application = FacesContext.getCurrentInstance().getApplication();
		if (application.getProjectStage() == ProjectStage.Development){
			log.warn("Project is in development stage.");
		}
		
		try{
			ic = new InitialContext();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
