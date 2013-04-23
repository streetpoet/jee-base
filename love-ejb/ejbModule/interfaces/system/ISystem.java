package interfaces.system;

import javax.ejb.Remote;

import com.spstudio.love.system.bean.UserInfo;


@Remote
public interface ISystem {
	
	public boolean createUser(UserInfo userInfo);

}