<?xml version="1.0" encoding="UTF-8"?>
<ejb-jar version="3.1" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd">

	<display-name>${project.projectCode}-ejb</display-name>
	<enterprise-beans>
		<session>
		    <ejb-name>${project.projectCode?cap_first}SystemBean</ejb-name>
		    <security-role-ref>
		        <role-name>anonymous</role-name>
		    </security-role-ref>
		    <security-identity>
		        <use-caller-identity />
		    </security-identity>
		</session>
	</enterprise-beans>
	<assembly-descriptor>

		<!-- However, the specification of security-role elements is still a recommended 
			practice to ensure portability across application servers and for deployment 
			descriptor maintenance. -->
		<security-role>
			<role-name>Admin</role-name>
		</security-role>
		<security-role>
			<role-name>MemberRole</role-name>
		</security-role>

		<!-- Permission Definition -->

		<method-permission>
		    <unchecked />
		    <method>
		        <ejb-name>${project.projectCode?cap_first}SystemBean</ejb-name>
		        <method-name>*</method-name>
		    </method>
		</method-permission>
		<method-permission>
		    <unchecked />
		    <method>
		        <ejb-name>${project.projectCode?cap_first}SystemSingletonBean</ejb-name>
		        <method-name>*</method-name>
		    </method>
		</method-permission>

	</assembly-descriptor>
</ejb-jar>