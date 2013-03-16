package com.spstudio.love.${module.moduleName}.producer;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class ${module.moduleName?cap_first}RemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@${module.moduleName?cap_first}RemoteBean
	I${module.moduleName?cap_first} produce(){
		I${module.moduleName?cap_first} ${module.moduleName}s = null;
 		try {
 			${module.moduleName}s = (I${module.moduleName?cap_first})loveDaemon.getInitialContext().lookup("${module.moduleName?cap_first}Bean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return ${module.moduleName}s;
	}
}
