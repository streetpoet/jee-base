package ${project.packageString}.system.producer;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.enterprise.inject.Produces;

import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}DataSource;

public class DataSourceProducer {
	
	@Produces
	@${project.projectCode?cap_first}DataSource
	@Resource(name = "${project.projectCode?cap_first}DB",
			mappedName = "java:/${project.projectCode?cap_first}DB",
			authenticationType = AuthenticationType.CONTAINER, 
			shareable = false, 
			description = "datasource of connection pool")
	javax.sql.DataSource dataSource = null;
}
