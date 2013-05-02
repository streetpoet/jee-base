package interfaces.system;

import javax.ejb.Remote;

import ${project.packageString}.system.bean.UserInfo;


@Remote
public interface I${project.projectCode?cap_first}System {
	
	public boolean createUser(UserInfo userInfo);

}