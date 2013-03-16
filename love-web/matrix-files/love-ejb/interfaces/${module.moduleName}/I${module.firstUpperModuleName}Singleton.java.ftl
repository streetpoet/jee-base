package interfaces.${module.moduleName};

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface I${module.firstUpperModuleName}Singleton {
	
	public List<String[]> retrieveDebugInfoList();
	
}
