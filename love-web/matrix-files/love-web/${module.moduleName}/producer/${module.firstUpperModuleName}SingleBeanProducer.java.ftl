package com.spstudio.love.${module.moduleName}.producer;

import interfaces.I${module.firstUpperModuleName}Singleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}SingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class ${module.firstUpperModuleName}SingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@${module.firstUpperModuleName}SingleRemoteBean
	I${module.firstUpperModuleName}Singleton produce(){
		I${module.firstUpperModuleName}Singleton ${module.moduleName}s = null;
 		try {
 			${module.moduleName}s = (I${module.firstUpperModuleName}Singleton)loveDaemon.getInitialContext().lookup("${module.firstUpperModuleName}SingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return ${module.moduleName}s;
	}
}
