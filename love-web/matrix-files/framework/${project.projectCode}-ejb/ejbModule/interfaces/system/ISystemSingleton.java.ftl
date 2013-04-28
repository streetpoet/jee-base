package interfaces.system;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface ISystemSingleton {
	
	public Map<String, List<String>> retrieveSystemConfiguration();
	
}
