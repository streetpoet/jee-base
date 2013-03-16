package impl.interest;

import interfaces.interest.IInterestSingleton;

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
public class InterestSingletonBean implements IInterestSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<String[]> techClassifyList = null;
	private String queryAllTechClassifySQL = "select "
											+"    c1.id, c1.class_name, c2.id, c2.class_name "
											+"from "
											+"    f3_class_1 c1, "
											+"    f3_class_2 c2 "
											+"where "
											+"    c1.id = c2.father_id "
											+"order by c1.id , c2.id";

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrieveTechClassifyList() {
		return techClassifyList;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void queryInterestType(){
		List<String[]> types = new ArrayList<String[]>();
		List<Object[]> result = helper.doQuery(queryAllTechClassifySQL, null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				String[] row = new String[4];
				row[IInterestSingleton.TechClassify.INDEX_ID] = String.valueOf(data[IInterestSingleton.TechClassify.INDEX_ID]);
				row[IInterestSingleton.TechClassify.INDEX_TECH_CLASS_1_NAME] = (String)data[IInterestSingleton.TechClassify.INDEX_TECH_CLASS_1_NAME];
				row[IInterestSingleton.TechClassify.INDEX_CLASS_2_ID] = String.valueOf(data[IInterestSingleton.TechClassify.INDEX_CLASS_2_ID]);
				row[IInterestSingleton.TechClassify.INDEX_TECH_CLASS_2_NAME] = (String)data[IInterestSingleton.TechClassify.INDEX_TECH_CLASS_2_NAME];
				types.add(row);
			}
		}
		 techClassifyList = types;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ InterestSingletonBean start. ]]");
		queryInterestType();
	}
}
