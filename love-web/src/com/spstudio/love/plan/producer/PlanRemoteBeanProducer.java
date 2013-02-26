package com.spstudio.love.plan.producer;

import interfaces.plan.IPlan;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.plan.qualifier.PlanRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class PlanRemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@PlanRemoteBean
	IPlan produce(){
		IPlan plans = null;
 		try {
 			plans = (IPlan)loveDaemon.getInitialContext().lookup("PlanBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return plans;
	}
}
