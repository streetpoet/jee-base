package com.spstudio.love.tbmarket.producer;

import interfaces.tbmarket.ITbmarket;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.tbmarket.qualifier.TbmarketRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class TbmarketRemoteBeanProducer {
	
	@EJB
	LoveDaemon LoveDaemon;
	
	@Produces
	@TbmarketRemoteBean
	ITbmarket produce(){
		ITbmarket tbmarkets = null;
 		try {
 			tbmarkets = (ITbmarket)LoveDaemon.getInitialContext().lookup("TbmarketBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return tbmarkets;
	}
}
