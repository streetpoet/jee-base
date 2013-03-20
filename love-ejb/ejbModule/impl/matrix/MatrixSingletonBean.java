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
	private List<MatrixProject> returnList = null;

	@Override
	@Lock(LockType.READ)
	public List<MatrixProject> retrieveProjectList() {
		return returnList;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		List<MatrixProject> projectList = new ArrayList<MatrixProject>();
		for(Object[] object: helper.doQuery(SQL, null)){
			MatrixProject mp = new MatrixProject();
			mp.setId((Integer)object[0]);
			mp.setProjectName((String)object[1]);
			projectList.add(mp);
		}
		returnList = projectList;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ MatrixSingletonBean start. ]]");
		timer();
	}
}
