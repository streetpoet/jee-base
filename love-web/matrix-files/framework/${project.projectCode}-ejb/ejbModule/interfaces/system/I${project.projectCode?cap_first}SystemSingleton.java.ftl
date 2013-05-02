package interfaces.system;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface I${project.projectCode?cap_first}SystemSingleton {
	
	public Map<String, List<String>> retrieveSystemConfiguration();
	
}
