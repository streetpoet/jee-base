package com.spstudio.love.web.common;

import java.sql.SQLException;
import java.util.Properties;

import org.jboss.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ExceptionInterceptor;

public class MySQLExceptionInterceptor implements ExceptionInterceptor {
	
	private Logger log = Logger.getLogger(MySQLExceptionInterceptor.class);
	
	@Override
	public void destroy() {
		log.info("MySQLExceptionInterceptor#destroy has been invoked.");
	}

	@Override
	public void init(Connection arg0, Properties arg1) throws SQLException {
		log.info("MySQLExceptionInterceptor#init has been invoked.");
	}

	@Override
	public SQLException interceptException(SQLException e, Connection conn) {
		log.info("MySQLExceptionInterceptor#interceptException has been invoked.");
		log.error("Message:" + e.getMessage());
		log.error("Error Code:" + e.getErrorCode());
		log.error("SQL State:" + e.getSQLState());
		return e;
	}

}
