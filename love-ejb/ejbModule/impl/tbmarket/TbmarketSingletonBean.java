package impl.tbmarket;

import interfaces.tbmarket.ITbmarket;
import interfaces.tbmarket.ITbmarketSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.system.helper.DatabaseHelper;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.tbmarket.entity.TbAcountTypeBean;
import com.spstudio.love.tbmarket.entity.TrafficInfoBean;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@AccessTimeout(unit = TimeUnit.MINUTES, value = 30)
public class TbmarketSingletonBean implements ITbmarketSingleton {
	
	@Inject @LoveLogged Logger log;
	@Inject DatabaseHelper helper;
	@Inject AutoSendMap sendMap;
	@Inject MailSender mailSender;
	@EJB ITbmarket tbMarketBean;
	
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
	
	@Schedule(minute = "*/5", hour = "17", persistent = false)
	public void sendTrafficMapMail(){
		List<TrafficInfoBean> trafficRequestInfo = tbMarketBean.getTrafficRequestInfo();
		for (TrafficInfoBean bean: trafficRequestInfo){
			sendMap.execute(bean.getFromPlace(), bean.getToPlace(), bean.getId());
			mailSender.sendMail(bean.getId());
		}
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("[[ TbmarketSingletonBean start. ]]");
		timer();
	}
}
