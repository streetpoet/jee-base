package impl.system;

import interfaces.system.I${project.projectCode?cap_first}SystemSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ${project.packageString}.system.helper.DatabaseHelper;
import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Logged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class ${project.projectCode?cap_first}SystemSingletonBean implements I${project.projectCode?cap_first}SystemSingleton {
	
	@Inject @${project.projectCode?cap_first}Logged Logger log;
	@Inject DatabaseHelper helper;
	
	private static final String LOAD_CONFIG_SQL =  "select  "
			+"    sc.key, sc.value "
			+"from "
			+"    sys_config sc "
			+"where "
			+"    sc.module_id = 0 "
			+"order by sc.key ";
	
	private Map<String, List<String>> returnMap = null;
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		Map<String, List<String>> configMap = new HashMap<String, List<String>>();
		String currentKey = "";
		for(Object[] object: helper.doQuery(LOAD_CONFIG_SQL, null)){
			String key = (String)object[0];
			if (! currentKey.equals(key)){
				currentKey = key;
				List<String> list = new ArrayList<String>();
				list.add((String)object[1]);
				configMap.put(currentKey, list);
				continue;
			}
			configMap.get(key).add((String)object[1]);
		}
		returnMap = configMap;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ SystemSingletonBean start. ]]");
		timer();
	}

	@Override
	public Map<String, List<String>> retrieveSystemConfiguration() {
		return returnMap;
	}
}
