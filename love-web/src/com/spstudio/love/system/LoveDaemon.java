package com.spstudio.love.system;


import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class LoveDaemon{

	private static Logger log = Logger.getLogger(LoveDaemon.class);
	private InitialContext ic;
	
	@PostConstruct
	public void postConstruct(){
		log.info("### LoveDaemon started ###");
	}
	
	@Lock(LockType.READ)
	public InitialContext getInitialContext(){
		if (ic == null){
			try{
				ic = new InitialContext();
	 			ic.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	 			ic.addToEnvironment(Context.PROVIDER_URL, "jnp://localhost:1099");
	 			ic.addToEnvironment(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
			}catch(Exception e){
				log.error(e);
			}
		}
		return ic;
	}
}
