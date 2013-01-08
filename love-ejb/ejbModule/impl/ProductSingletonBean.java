package impl;

import interfaces.IProductSingleton;

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

import com.spstudio.love.web.helper.DatabaseHelper;
import com.spstudio.love.web.qualifiers.LoveLogged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class ProductSingletonBean implements IProductSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<String[]> productClassify = null;

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrieveProductClassify() {
		return productClassify;
	}
	
	@Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
	public void queryProductClassify(){
		List<String[]> classify = new ArrayList<String[]>();
		List<Object[]> result = helper.doQuery("select id, kindName from f1_classify", null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				String[] row = new String[2];
				row[INDEX_CLASSIFY_ID] = String.valueOf(data[0]);
				row[INDEX_CLASSIFY_NAME] = (String)data[1];
				classify.add(row);
			}
		}
		productClassify = classify;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ ProductSingletonBean start. ]]");
		queryProductClassify();
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info("[[ ProductSingletonBean end. ]]");
	}

}
