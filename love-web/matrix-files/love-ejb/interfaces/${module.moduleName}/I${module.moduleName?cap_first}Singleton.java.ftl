package interfaces.${module.moduleName};

import java.util.List;
import javax.ejb.Remote;
import com.spstudio.love.sample.entity.${module.selectBeanName?cap_first};

@Remote
public interface I${module.moduleName?cap_first}Singleton {
	
	public List<${module.selectBeanName?cap_first}> retrieve${module.selectBeanName?cap_first}List();
	
}
