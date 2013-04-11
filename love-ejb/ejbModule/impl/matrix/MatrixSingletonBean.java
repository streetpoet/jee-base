package impl.matrix;

import interfaces.matrix.IMatrixSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.qualifier.LoveLogged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class MatrixSingletonBean implements IMatrixSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private static final String SQL = "select id, project_name from f4_project order by id";
	private static final String LOAD_CONFIG_SQL =  "select  "
												+"    sc.key, sc.value "
												+"from "
												+"    sys_config sc "
												+"where "
												+"    sc.module_id = 1 "
												+"order by sc.key ";

	private List<MatrixProject> returnList = null;
	private Map<String, List<String>> returnConfigMap;

	@Override
	@Lock(LockType.READ)
	public List<MatrixProject> retrieveProjectList() {
		return returnList;
	}
	
	@Override
	public Map<String, List<String>> retrieveGenerationConfiguration() {
		return returnConfigMap;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		refreshProjectCache();
		
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
		returnConfigMap = configMap;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ MatrixSingletonBean start. ]]");
		timer();
	}

	@Override
	public void refreshProjectCache() {
		List<MatrixProject> projectList = new ArrayList<MatrixProject>();
		for(Object[] object: helper.doQuery(SQL, null)){
			MatrixProject mp = new MatrixProject();
			mp.setId((Integer)object[0]);
			mp.setProjectName((String)object[1]);
			projectList.add(mp);
		}
		returnList = projectList;
	}

}
