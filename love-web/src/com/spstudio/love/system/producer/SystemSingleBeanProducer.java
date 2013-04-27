package com.spstudio.love.system.producer;

import interfaces.system.ISystemSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.system.qualifier.SystemSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class SystemSingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@SystemSingleRemoteBean
	ISystemSingleton produce(){
		ISystemSingleton systems = null;
 		try {
 			systems = (ISystemSingleton)loveDaemon.getInitialContext().lookup("SystemSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return systems;
	}
}
