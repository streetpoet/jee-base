package interfaces.${module.moduleName};

import javax.ejb.Remote;

@Remote
public interface I${module.moduleName?cap_first} {
	public String debug(String debug);
}