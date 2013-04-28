package interfaces.system;

import javax.ejb.Remote;

import ${project.packageString}.system.bean.UserInfo;


@Remote
public interface ISystem {
	
	public boolean createUser(UserInfo userInfo);

}