package interfaces.${module.moduleName};

import javax.ejb.Remote;

@Remote
public interface I${module.firstUpperModuleName} {
	public String debug(String debug);
}
