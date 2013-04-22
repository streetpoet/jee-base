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

import com.spstudio.love.interest.entity.TechTypeBean;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.qualifier.LoveLogged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class InterestSingletonBean implements IInterestSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<TechTypeBean> returnList = null;
	private String queryAllTechClassifySQL = "select "
											+"    c1.id, c1.class_name, c2.id, c2.class_name "
											+"from "
											+"    f3_class_1 c1, "
											+"    f3_class_2 c2 "
											+"where "
											+"    c1.id = c2.father_id "
											+"order by c1.id , c2.id";
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		List<TechTypeBean> list = new ArrayList<TechTypeBean>();
		
		List<Object[]> result = helper.doQuery(queryAllTechClassifySQL, null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				TechTypeBean bean = new TechTypeBean();
				bean.setClassifyFirstId((Integer)data[0]);
				bean.setClassifyFirstLabel((String)data[1]);
				bean.setClassifySecondId((Integer)data[2]);
				bean.setClassifySecondLabel((String)data[3]);
				list.add(bean);
			}
		}
		returnList = list;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ InterestSingletonBean start. ]]");
		timer();
	}

	@Override
	@Lock(LockType.READ)
	public List<TechTypeBean> retrieveTechTypeBeanList() {
		return returnList;
	}
}
