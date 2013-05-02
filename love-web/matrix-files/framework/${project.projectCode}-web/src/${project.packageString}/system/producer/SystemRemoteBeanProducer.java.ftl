package ${project.packageString}.system.producer;

import interfaces.system.I${project.projectCode?cap_first}System;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import ${project.packageString}.system.qualifier.SystemRemoteBean;
import ${project.packageString}.system.${project.projectCode?cap_first}Daemon;

public class SystemRemoteBeanProducer {
	
	@EJB
	${project.projectCode?cap_first}Daemon ${project.projectCode}Daemon;
	
	@Produces
	@SystemRemoteBean
	I${project.projectCode?cap_first}System produce(){
		I${project.projectCode?cap_first}System systems = null;
 		try {
 			systems = (I${project.projectCode?cap_first}System)${project.projectCode}Daemon.getInitialContext().lookup("${project.projectCode?cap_first}SystemBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return systems;
	}
}
