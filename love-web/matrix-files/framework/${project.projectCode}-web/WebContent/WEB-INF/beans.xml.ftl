<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://java.sun.com/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://docs.jboss.org/cdi/beans_1_0.xsd">
	<interceptors>
		<class>${project.packageString}.system.interceptor.${project.projectCode?cap_first}TraceInteceptor</class>
	</interceptors>
</beans>