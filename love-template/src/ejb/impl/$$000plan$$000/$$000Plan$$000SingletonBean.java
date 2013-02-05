package impl.plan;

import interfaces.$$999plan$$999.I$$000Plan$$000Singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
public class $$000Plan$$000SingletonBean implements I$$000Plan$$000Singleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	//TODO: copy begin
	private List<String[]> $$003planType$$003List = null;

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrieve$$001PlanType$$001List() {
		return $$003planType$$003List;
	}
	
	@Schedule(minute = "*/1", hour = "*", persistent = false)
	public void query$$001PlanTypes$$001(){
		List<String[]> types = new ArrayList<String[]>();
		List<Object[]> result = helper.doQuery("$$004sql$$004", null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				String[] row = new String[2];
				row[I$$000Plan$$000Singleton.$$001PlanType$$001.INDEX_$$002PlanType$$002_ID] = String.valueOf(data[0]);
				row[I$$000Plan$$000Singleton.$$001PlanType$$001.INDEX_$$002PlanType$$002_NAME] = (String)data[1];
				types.add(row);
			}
		}
		 $$003planType$$003List = types;
	}
	
	//TODO: copy end
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ PlanSingletonBean start. ]]");
		//TODO: copy begin
		query$$001PlanTypes$$001();
		//TODO: copy end
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info("[[ PlanSingletonBean end. ]]");
	}

}
