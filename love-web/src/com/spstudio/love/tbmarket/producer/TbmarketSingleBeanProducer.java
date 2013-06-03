package com.spstudio.love.tbmarket.producer;

import interfaces.tbmarket.ITbmarketSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.tbmarket.qualifier.TbmarketSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class TbmarketSingleBeanProducer {
	
	@EJB
	LoveDaemon LoveDaemon;
	
	@Produces
	@TbmarketSingleRemoteBean
	ITbmarketSingleton produce(){
		ITbmarketSingleton tbmarkets = null;
 		try {
 			tbmarkets = (ITbmarketSingleton)LoveDaemon.getInitialContext().lookup("TbmarketSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return tbmarkets;
	}
}
