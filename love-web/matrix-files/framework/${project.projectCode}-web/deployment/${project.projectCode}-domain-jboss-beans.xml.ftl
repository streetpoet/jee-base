<?xml version="1.0" encoding="UTF-8"?>

<deployment xmlns="urn:jboss:bean-deployer:2.0">
	<application-policy xmlns="urn:jboss:security-beans:1.0" name="${project.projectCode}-domain">
		<authentication>
			
			<login-module code="org.jboss.security.auth.spi.DatabaseServerLoginModule" flag="required">
				<module-option name="dsJndiName">java:/${project.projectCode?cap_first}DB</module-option>
				<module-option name="principalsQuery">select pwd from users where username = ?</module-option>
				<module-option name="rolesQuery">select roles.roleName, 'Roles' from users, roles, userRoleRef where users.id = userRoleRef.userId and roles.id = userRoleRef.roleId and  users.username = ? </module-option>
				<module-option name="password-stacking">useFirstPass</module-option>
				<module-option name="hashAlgorithm">MD5</module-option> <!-- MD5, SHA -->
				<module-option name="hashEncoding">base64</module-option> <!-- base64, hex or rfc2617 -->
				<module-option name="hashUserPassword">true</module-option>
			</login-module>

		</authentication>
		
	</application-policy>
</deployment>