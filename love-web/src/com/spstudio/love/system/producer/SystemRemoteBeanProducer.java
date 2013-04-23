package com.spstudio.love.system.producer;

import interfaces.system.ISystem;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.system.qualifier.SystemRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class SystemRemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@SystemRemoteBean
	ISystem produce(){
		ISystem systems = null;
 		try {
 			systems = (ISystem)loveDaemon.getInitialContext().lookup("SystemBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return systems;
	}
}
