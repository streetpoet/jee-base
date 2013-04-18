package com.spstudio.love.interest.producer;

import interfaces.interest.IInterestSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.interest.qualifier.InterestSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class InterestSingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@InterestSingleRemoteBean
	IInterestSingleton produce(){
		IInterestSingleton interests = null;
 		try {
 			interests = (IInterestSingleton)loveDaemon.getInitialContext().lookup("InterestSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return interests;
	}
}
