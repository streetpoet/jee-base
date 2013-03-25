package com.spstudio.love.sample.producer;

import interfaces.sample.ISampleSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.sample.qualifier.SampleSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class SampleSingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@SampleSingleRemoteBean
	ISampleSingleton produce(){
		ISampleSingleton samples = null;
 		try {
 			samples = (ISampleSingleton)loveDaemon.getInitialContext().lookup("SampleSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return samples;
	}
}
