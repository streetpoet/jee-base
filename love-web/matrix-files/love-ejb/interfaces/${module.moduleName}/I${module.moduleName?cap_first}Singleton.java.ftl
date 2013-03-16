package interfaces.${module.moduleName};

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface I${module.moduleName?cap_first}Singleton {
	
	public List<String[]> ${module.singletonEjbMethodName}();
	
}
