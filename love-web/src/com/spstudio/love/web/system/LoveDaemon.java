package com.spstudio.love.web.system;

import javax.faces.application.Application;
import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;

@ApplicationScoped
@ManagedBean(eager = true)
public class LoveDaemon {
	
	private Logger log = Logger.getLogger(LoveDaemon.class);
	
	private static InitialContext CTX = null;
	private static DataSource DATASOURCE = null;
	
	public LoveDaemon(){
		log.info("LoveDaemon started.");
		
		/*
		 * check project status
		 */
		Application application = FacesContext.getCurrentInstance().getApplication();
		if (application.getProjectStage() == ProjectStage.Development){
			log.warn("Project is in development stage.");
		}
		
		/*
		 * 
		 */
		try{
			CTX = new InitialContext();
		}catch(NamingException e){
			log.error("Can't create InitialContext Object.", e);
		}
		
		/*
		 * 
		 */
		try{
			DATASOURCE = (DataSource)CTX.lookup("java:LoveWebDB");
		}catch(NamingException e){
			log.error("Can't lookup jndi database.", e);
		}
	}
	
	public static DataSource getDataSource(){
		if (DATASOURCE != null){
			return DATASOURCE;
		}
		return null;
	}
}
