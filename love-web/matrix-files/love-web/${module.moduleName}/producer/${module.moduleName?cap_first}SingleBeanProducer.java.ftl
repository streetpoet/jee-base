package ${project.packageString}.${module.moduleName}.producer;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first}Singleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import ${project.packageString}.${module.moduleName}.qualifier.${module.moduleName?cap_first}SingleRemoteBean;
import ${project.packageString}.system.${project.projectCode?cap_first}Daemon;

public class ${module.moduleName?cap_first}SingleBeanProducer {
	
	@EJB
	${project.projectCode?cap_first}Daemon ${project.projectCode?cap_first}Daemon;
	
	@Produces
	@${module.moduleName?cap_first}SingleRemoteBean
	I${module.moduleName?cap_first}Singleton produce(){
		I${module.moduleName?cap_first}Singleton ${module.moduleName}s = null;
 		try {
 			${module.moduleName}s = (I${module.moduleName?cap_first}Singleton)${project.projectCode?cap_first}Daemon.getInitialContext().lookup("${module.moduleName?cap_first}SingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return ${module.moduleName}s;
	}
}
