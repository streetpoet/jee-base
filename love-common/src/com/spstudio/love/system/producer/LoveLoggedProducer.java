package com.spstudio.love.system.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

import com.spstudio.love.system.qualifier.LoveLogged;

public class LoveLoggedProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@LoveLogged
	private Logger produceLogger(InjectionPoint injectionPoint){		
 		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
