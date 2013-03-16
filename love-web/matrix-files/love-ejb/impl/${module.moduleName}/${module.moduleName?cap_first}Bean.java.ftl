package impl.${module.moduleName};

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.system.helper.DatabaseHelper;

@Stateless
public class ${module.moduleName?cap_first}Bean implements I${module.moduleName?cap_first} {

	@Inject DatabaseHelper helper;
	
	private static final String SQL = "insert into table debug values(?)";
	
	@Override
	public String debug(String debug){
		Object[] params = new Object[]{
			"debug"
		};
		helper.doDMLOperation(SQL, params);
		return "success";
	}

}
