package com.spstudio.love.web.producer;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.enterprise.inject.Produces;

import com.spstudio.love.web.qualifiers.LoveDataSource;

public class DataSourceProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@LoveDataSource
	@Resource(name = "LoveWebDB",
			mappedName = "java:/LoveWebDB",
			authenticationType = AuthenticationType.CONTAINER, 
			shareable = false, 
			description = "datasource of connection pool")
	private javax.sql.DataSource dataSource = null;
}
