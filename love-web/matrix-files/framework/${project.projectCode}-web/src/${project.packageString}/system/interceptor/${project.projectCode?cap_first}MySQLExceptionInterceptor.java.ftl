package ${project.packageString}.system.interceptor;

import java.sql.SQLException;
import java.util.Properties;

import org.jboss.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ExceptionInterceptor;

public class ${project.projectCode?cap_first}MySQLExceptionInterceptor implements ExceptionInterceptor {
	
	private Logger log = Logger.getLogger(MySQLExceptionInterceptor.class);
	
	@Override
	public SQLException interceptException(SQLException e, Connection conn) {
		log.info("MySQLExceptionInterceptor#interceptException has been invoked.");
		log.error("Message:" + e.getMessage());
		log.error("Error Code:" + e.getErrorCode());
		log.error("SQL State:" + e.getSQLState());
		return e;
	}

	@Override
	public void destroy() {}

	@Override
	public void init(Connection arg0, Properties arg1) throws SQLException {}

}
