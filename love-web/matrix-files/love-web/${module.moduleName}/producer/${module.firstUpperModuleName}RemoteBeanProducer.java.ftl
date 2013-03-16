package com.spstudio.love.${module.moduleName}.producer;

import interfaces.I${module.firstUpperModuleName};

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}RemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class ${module.firstUpperModuleName}RemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@${module.firstUpperModuleName}RemoteBean
	I${module.firstUpperModuleName} produce(){
		I${module.firstUpperModuleName} ${module.moduleName}s = null;
 		try {
 			${module.moduleName}s = (I${module.firstUpperModuleName})loveDaemon.getInitialContext().lookup("${module.firstUpperModuleName}Bean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return ${module.moduleName}s;
	}
}
