package impl.matrix;

import interfaces.matrix.IMatrixSingleton;

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
public class MatrixSingletonBean implements IMatrixSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private static final String SQL = "select id, project_name from f4_project order by id";
	private List<String[]> returnList = null;

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrieveProjectList() {
		return returnList;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		List<String[]> projectList = new ArrayList<String[]>();
		for(Object[] object: helper.doQuery(SQL, null)){
			projectList.add(new String[]{String.valueOf(object[0]), (String)object[1]});
		}
		returnList = projectList;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ MatrixSingletonBean start. ]]");
		timer();
	}
}
