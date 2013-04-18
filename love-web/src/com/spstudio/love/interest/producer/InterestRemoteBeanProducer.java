package com.spstudio.love.interest.producer;

import interfaces.interest.IInterest;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.interest.qualifier.InterestRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class InterestRemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@InterestRemoteBean
	IInterest produce(){
		IInterest interests = null;
 		try {
 			interests = (IInterest)loveDaemon.getInitialContext().lookup("InterestBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return interests;
	}
}
