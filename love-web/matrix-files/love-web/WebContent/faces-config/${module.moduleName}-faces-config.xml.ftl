<?xml version="1.0" encoding="UTF-8"?>
<faces-config version="2.0" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xi="http://www.w3.org/2001/XInclude" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd">
	
	<navigation-rule>
	    <from-view-id>/pages/${module.moduleName}/${function.functionName}.xhtml</from-view-id>
	    <navigation-case>
	        <from-outcome>SUCCESS</from-outcome>
	        <to-view-id>/pages/${module.moduleName}/${function.functionName}.xhtml</to-view-id>
	        <redirect></redirect>
	    </navigation-case>
	</navigation-rule>
	
</faces-config>