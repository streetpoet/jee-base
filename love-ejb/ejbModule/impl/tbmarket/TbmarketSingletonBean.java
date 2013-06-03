package impl.tbmarket;

import interfaces.tbmarket.ITbmarketSingleton;

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

import com.spstudio.love.tbmarket.entity.TbAcountTypeBean;
import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.qualifier.LoveLogged;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.SECONDS, value = 5)
public class TbmarketSingletonBean implements ITbmarketSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	
	private List<TbAcountTypeBean> returnList = null;

	@Override
	@Lock(LockType.READ)
	public List<TbAcountTypeBean> retrieveTbAcountTypeBeanList() {
		return returnList;
	}
	
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void timer(){
		List<TbAcountTypeBean> list = new ArrayList<TbAcountTypeBean>();
		//TODO: Write logic here, add values to 'list'.
		returnList = list;
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ TbmarketSingletonBean start. ]]");
		timer();
	}
}
