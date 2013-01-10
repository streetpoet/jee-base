package com.spstudio.love.system;

import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

@ApplicationScoped
@ManagedBean(eager = true)
public class LoveDaemon{

	Logger log = Logger.getLogger(LoveDaemon.class);
	
	public static InitialContext ic;
	
	static{
		try{
			ic = new InitialContext();
 			ic.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
 			ic.addToEnvironment(Context.PROVIDER_URL, "jnp://localhost:1099");
 			ic.addToEnvironment(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
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
}
