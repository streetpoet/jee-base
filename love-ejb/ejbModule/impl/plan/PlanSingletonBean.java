package impl.plan;

import interfaces.plan.IPlanSingleton;

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
public class PlanSingletonBean implements IPlanSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<String[]> planTypeList = null;
	private List<String[]> planUnitList = null;

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrievePlanTypes() {
		return planTypeList;
	}
	
	@Override
	@Lock(LockType.READ)
	public List<String[]> retrievePlanUnits() {
		return planUnitList;
	}
	
	@Schedule(minute = "*/1", hour = "*", persistent = false)
	public void queryPlanTypes(){
		List<String[]> types = new ArrayList<String[]>();
		List<Object[]> result = helper.doQuery("select id, typeName from f2_plan_type order by id", null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				String[] row = new String[2];
				row[IPlanSingleton.PlanType.INDEX_TYPE_ID] = String.valueOf(data[0]);
				row[IPlanSingleton.PlanType.INDEX_TYPE_NAME] = (String)data[1];
				types.add(row);
			}
		}
		planTypeList = types;
	}
	
	@Schedule(minute = "*/1", hour = "*", persistent = false)
	public void queryPlanUnits(){
		List<String[]> types = new ArrayList<String[]>();
		List<Object[]> result = helper.doQuery("select id, unitName from f2_plan_unit order by id", null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				String[] row = new String[2];
				row[IPlanSingleton.PlanUnit.INDEX_UNIT_ID] = String.valueOf(data[0]);
				row[IPlanSingleton.PlanUnit.INDEX_UNIT_NAME] = (String)data[1];
				types.add(row);
			}
		}
		planUnitList = types;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ PlanSingletonBean start. ]]");
		queryPlanTypes();
		queryPlanUnits();
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info("[[ PlanSingletonBean end. ]]");
	}

}
