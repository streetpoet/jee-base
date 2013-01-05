package com.spstudio.love.web.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.LoveLogged;

@Interceptor
@LoveLogged
public class LoveLoggedInteceptor {
	
	private Logger log = Logger.getLogger(LoveLoggedInteceptor.class.getSimpleName());
	
	@AroundInvoke
	public java.lang.Object invoke(javax.interceptor.InvocationContext context) throws Exception{
		log.info("Entering method: " + context.getMethod().getDeclaringClass().getSimpleName() + "#" + context.getMethod().getName());
		return context.proceed();
	}
	
}
