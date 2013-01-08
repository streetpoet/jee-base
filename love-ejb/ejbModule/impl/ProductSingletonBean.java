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
	
	boolean b = true;
	
	@Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
	public void queryProductClassify(){
		if (b){
			System.out.println("b = " + b);
			b = false;
			helper.doQuery("select * from users", null);
			
		}
//		List<String[]> classify = new ArrayList<String[]>();
//		List<Object[]> result = helper.doQuery("select id, kindName from f1_classify", null);
//		if (result != null && result.size() != 0){
//			for (Object[] data: result){
//				String[] row = new String[2];
//				row[INDEX_CLASSIFY_ID] = String.valueOf(data[0]);
//				row[INDEX_CLASSIFY_NAME] = (String)data[1];
//				classify.add(row);
//			}
//		}
//		productClassify = classify;
	}

}
