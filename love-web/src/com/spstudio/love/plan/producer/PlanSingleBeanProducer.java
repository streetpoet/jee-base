package com.spstudio.love.plan.producer;

import interfaces.plan.IPlanSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.plan.qualifier.PlanSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class PlanSingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@PlanSingleRemoteBean
	IPlanSingleton produce(){
		IPlanSingleton plan = null;
 		try {
 			plan = (IPlanSingleton)loveDaemon.getInitialContext().lookup("PlanSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return plan;
	}
}
