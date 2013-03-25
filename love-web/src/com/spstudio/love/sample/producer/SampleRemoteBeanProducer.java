package com.spstudio.love.sample.producer;

import interfaces.sample.ISample;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.sample.qualifier.SampleRemoteBean;
import com.spstudio.love.system.LoveDaemon;

public class SampleRemoteBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@Produces
	@SampleRemoteBean
	ISample produce(){
		ISample samples = null;
 		try {
 			samples = (ISample)loveDaemon.getInitialContext().lookup("SampleBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return samples;
	}
}
