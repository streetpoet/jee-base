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

import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.qualifier.LoveLogged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class ${module.moduleName?cap_first}SingletonBean implements I${module.moduleName?cap_first}Singleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<String[]> debugInfoList = null;
	private String SQL = "";

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrieveDebugInfoList() {
		return debugInfoList;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void queryDebugListType(){
		List<String[]> types = new ArrayList<String[]>();
		debugInfoList = types;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ ${module.moduleName?cap_first}SingletonBean start. ]]");
		queryDebugListType();
	}
}
