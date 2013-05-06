package impl.${module.moduleName};

import interfaces.${module.moduleName}.I${module.moduleName?cap_first}Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ${project.packageString}.${module.moduleName}.entity.${module.selectBeanName?cap_first};
import ${project.packageString}.system.helper.DatabaseHelper;
import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Logged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class ${module.moduleName?cap_first}SingletonBean implements I${module.moduleName?cap_first}Singleton {
	
	@Inject @${project.projectCode?cap_first}Logged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<${module.selectBeanName?cap_first}> returnList = null;

	@Override
	@Lock(LockType.READ)
	public List<${module.selectBeanName?cap_first}> retrieve${module.selectBeanName?cap_first}List() {
		return returnList;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		List<${module.selectBeanName?cap_first}> list = new ArrayList<${module.selectBeanName?cap_first}>();
		//TODO: Write logic here, add values to 'list'.
		returnList = list;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ ${module.moduleName?cap_first}SingletonBean start. ]]");
		timer();
	}
}
