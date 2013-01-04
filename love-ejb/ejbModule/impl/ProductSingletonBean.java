package impl;

import helper.DatabaseHelper;
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

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class ProductSingletonBean implements IProductSingleton {
	
	private Logger log = Logger.getLogger(ProductSingletonBean.class);
	private List<String[]> productClassify;
	
	@Inject
	private DatabaseHelper helper;
	
	@PostConstruct
	public void postConstruct(){
		log.info(ProductSingletonBean.class.getName() + "#postConstruct");
		productClassify = new ArrayList<String[]>();
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info(ProductSingletonBean.class.getName() + "#preDestroy");
	}

	@Override
	@Lock(LockType.READ)
	public List<String[]> retrieveProductClassify() {
		return productClassify;
	}
	
	@Schedule(second = "*/15", minute = "*", hour = "*", persistent = false)
	public void queryProductClassify(){
		List<String[]> classify = new ArrayList<String[]>();
		List<Object[]> result = helper.doQuery("select id, kindName from f1_classify", null);
		if (result != null && result.size() != 0){
			for (Object[] data: result){
				classify.add(new String[]{String.valueOf(data[0]), (String)data[1]});
			}
		}
		productClassify = classify;
	}

}
