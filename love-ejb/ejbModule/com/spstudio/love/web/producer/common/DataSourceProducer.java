package com.spstudio.love.web.producer.common;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.enterprise.inject.Produces;

import com.spstudio.love.web.qualifiers.system.LoveDataSource;

public class DataSourceProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@LoveDataSource
	@Resource(name = "LoveDB",
			mappedName = "java:/LoveDB",
			authenticationType = AuthenticationType.CONTAINER, 
			shareable = false, 
			description = "datasource of connection pool")
	private javax.sql.DataSource dataSource = null;
}
