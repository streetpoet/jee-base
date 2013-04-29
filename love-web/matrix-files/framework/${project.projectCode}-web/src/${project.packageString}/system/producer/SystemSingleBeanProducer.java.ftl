package ${project.packageString}.system.producer;

import interfaces.system.ISystemSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import ${project.packageString}.system.qualifier.SystemSingleRemoteBean;
import ${project.packageString}.system.LoveDaemon;

public class SystemSingleBeanProducer {
	
	@EJB
	@${project.projectCode?cap_first}Daemon ${project.projectCode}Daemon;
	
	@Produces
	@SystemSingleRemoteBean
	ISystemSingleton produce(){
		ISystemSingleton systems = null;
 		try {
 			systems = (ISystemSingleton)${project.projectCode}Daemon.getInitialContext().lookup("SystemSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return systems;
	}
}
