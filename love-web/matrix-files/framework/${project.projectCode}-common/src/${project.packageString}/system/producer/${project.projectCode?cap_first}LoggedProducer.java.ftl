package ${project.packageString}.system.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Logged;

public class ${project.projectCode?cap_first}LoggedProducer {
	
	@Produces
	@${project.projectCode?cap_first}Logged
	Logger produceLogger(InjectionPoint injectionPoint){		
 		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
