package ${project.packageString}.system.producer;

import interfaces.system.I${project.projectCode?cap_first}SystemSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}SystemSingleRemoteBean;
import ${project.packageString}.system.${project.projectCode?cap_first}Daemon;

public class SystemSingleBeanProducer {
	
	@EJB
	${project.projectCode?cap_first}Daemon ${project.projectCode}Daemon;
	
	@Produces
	@SystemSingleRemoteBean
	I${project.projectCode?cap_first}SystemSingleton produce(){
		I${project.projectCode?cap_first}SystemSingleton systems = null;
 		try {
 			systems = (I${project.projectCode?cap_first}SystemSingleton)${project.projectCode}Daemon.getInitialContext().lookup("${project.projectCode?cap_first}SystemSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return systems;
	}
}
