<datasources>
	<local-tx-datasource>

	    <!-- ************** -->
	    <!-- JBoss Settings -->
	    <!-- ************** -->
	    
		<jndi-name>${project.projectCode?cap_first}DB</jndi-name>
		<use-java-context>true</use-java-context>
		<connection-url>jdbc:mysql:loadbalance://127.0.0.1:3306/${project.projectCode}</connection-url>
		<driver-class>com.mysql.jdbc.Driver</driver-class>
		<!-- Pooling parameters -->
		<min-pool-size>5</min-pool-size>
		<max-pool-size>20</max-pool-size>
		<idle-timeout-minutes>5</idle-timeout-minutes>
		<prefill>true</prefill>
		<transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
		<!-- Valid connection checking -->
		<valid-connection-checker-class-name>
			com.mysql.jdbc.integration.jboss.MysqlValidConnectionChecker
		</valid-connection-checker-class-name>
		<!-- Errors during SQL queries -->
		<exception-sorter-class-name>
			com.mysql.jdbc.integration.jboss.ExtendedMysqlExceptionSorter
		</exception-sorter-class-name>
		
		<!-- ********************* -->
		<!-- Mysql Vendor Settings -->
		<!-- ********************* -->
		
		<!-- Connection/Authentication -->
		<connection-property name="user">sp</connection-property>
		<connection-property name="password">213231</connection-property>
		<connection-property name="localSocketAddress">127.0.0.1</connection-property>
		<connection-property name="useCompression">false</connection-property>
		
		<!-- Networking -->
		<connection-property name="maxAllowedPacket">-1</connection-property>
		
		<!-- High Availability and Clustering -->
		<connection-property name="autoReconnect">false</connection-property>
		
		<!-- Security -->
		<connection-property name="allowLoadLocalInfile">false</connection-property>
		<connection-property name="allowUrlInLocalInfile">false</connection-property>
		
		<!-- Performance Extensions -->
		<connection-property name="prepStmtCacheSize">25</connection-property>
		<connection-property name="prepStmtCacheSqlLimit">512</connection-property>
		<connection-property name="alwaysSendSetIsolation">false</connection-property>
		<connection-property name="enableQueryTimeouts">false</connection-property>
		<connection-property name="loadBalanceStrategy">bestResponseTime</connection-property>
		
		<!-- Miscellaneous -->
		<connection-property name="useUnicode">true</connection-property>
		<connection-property name="characterEncoding">UTF-8</connection-property>
		<connection-property name="characterSetResults">UTF-8</connection-property>
		<connection-property name="loadBalanceConnectionGroup">web-group</connection-property>
		<connection-property name="loadBalanceEnableJMX">true</connection-property> 
		<connection-property name="exceptionInterceptors">${project.packageString}.web.system.${project.projectCode?cap_first}MySQLExceptionInterceptor</connection-property>
		
		<!-- ********************************** -->
		<!-- Development Purpose, Debug Purpose -->
		<!-- ********************************** -->
		
		<connection-property name="gatherPerfMetrics">false</connection-property>
		<connection-property name="reportMetricsIntervalMillis">10000</connection-property>
		<connection-property name="profileSQL">false</connection-property>
		<connection-property name="logSlowQueries">false</connection-property>
		<connection-property name="slowQueryThresholdMillis">2000</connection-property>
		<connection-property name="explainSlowQueries">false</connection-property>
		<!--<connection-property name="useUsageAdvisor">true</connection-property>   -->
		<connection-property name="resultSetSizeThreshold">100</connection-property>
		<connection-property name="autoGenerateTestcaseScript">false</connection-property>
		<connection-property name="dumpMetadataOnColumnNotFound">false</connection-property>
		<connection-property name="dumpQueriesOnException">false</connection-property>
		<connection-property name="includeThreadDumpInDeadlockExceptions">false</connection-property>
		<!-- a warning message is issued for each unclosed statement, log4j should be in trace level -->
		<track-statements>false</track-statements>
		
	</local-tx-datasource>
</datasources> 