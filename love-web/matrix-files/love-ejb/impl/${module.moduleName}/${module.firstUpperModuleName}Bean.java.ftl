package impl.${module.moduleName};

import interfaces.${module.moduleName}.I${module.firstUpperModuleName};

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.system.helper.DatabaseHelper;

@Stateless
public class ${module.firstUpperModuleName}Bean implements I${module.firstUpperModuleName} {

	@Inject DatabaseHelper helper;
	
	private static final String SQL = "insert into table debug values(?)";
	
	@Override
	public String debug(String debug);
		Object[] params = new Object[]{
			"debug"
		};
		helper.doDMLOperation(SQL, params) == 1
		return "success";
	}

}
