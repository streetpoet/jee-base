package ${project.packageString}.system.producer;

import interfaces.system.ISystem;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import ${project.packageString}.system.qualifier.SystemRemoteBean;
import ${project.packageString}.system.LoveDaemon;

public class SystemRemoteBeanProducer {
	
	@EJB
	${project.projectCode?cap_first}Daemon ${project.projectCode}Daemon;
	
	@Produces
	@SystemRemoteBean
	ISystem produce(){
		ISystem systems = null;
 		try {
 			systems = (ISystem)${project.projectCode}Daemon.getInitialContext().lookup("SystemBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return systems;
	}
}
