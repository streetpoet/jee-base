
=================================
product environment preparation
=================================

1, copy the .jar file that comes with Connector/J to the lib directory for your server configuration (which is usually called default)
2, modify log level, jboss-logging.xml
3, jboss modification:
	1, use weld-api-1.1.Final.jar replace jboss's default impl, which is 1.1beta2
	2, use mojarra-2.0.10 replace jboss's default impl, which is mojarra-2.0.3b05 (update jboss jsf.deployer folder)