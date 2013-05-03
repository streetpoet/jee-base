<?xml version="1.0" encoding="UTF-8"?>
<faces-config version="2.0" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xi="http://www.w3.org/2001/XInclude" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd">

	<application>
	
		<system-event-listener>
			<system-event-listener-class>${project.packageString}.system.listener.${project.projectCode?cap_first}SysEventListener</system-event-listener-class>
			<system-event-class>javax.faces.event.PostConstructApplicationEvent</system-event-class>
		</system-event-listener>
		
		<locale-config>
			<!-- refer to http://ftp.ics.uci.edu/pub/ietf/http/related/iso639.txt -->
			<default-locale>en</default-locale>
			<supported-locale>zh</supported-locale>
			<supported-locale>ja</supported-locale>
		</locale-config>

		<!-- Resource Bundles -->
		<resource-bundle>
			<base-name>messages.${module.moduleName}.Message</base-name>
			<var>${module.moduleName}Bundle</var>
		</resource-bundle>
		
	</application>
	<factory>
	    <exception-handler-factory>${project.packageString}.system.exception.${project.projectCode?cap_first}ExceptionHandlerFactory</exception-handler-factory>
	</factory>

	<!-- 
	<lifecycle>
		<phase-listener>${project.packageString}.system.listener.${project.projectCode?cap_first}LifeCycleListener</phase-listener>
	</lifecycle>
	 -->
 
</faces-config>
