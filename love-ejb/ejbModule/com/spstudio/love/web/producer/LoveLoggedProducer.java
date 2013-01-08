package com.spstudio.love.web.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.LoveLogged;

public class LoveLoggedProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@LoveLogged
	private Logger produceLogger(InjectionPoint injectionPoint){		
 		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
