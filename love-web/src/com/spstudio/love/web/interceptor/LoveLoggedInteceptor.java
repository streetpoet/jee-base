package com.spstudio.love.web.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.LoveLogged;

@Interceptor
@LoveLogged
public class LoveLoggedInteceptor {
	
	private Logger log = Logger.getLogger(LoveLoggedInteceptor.class);
	
	@AroundInvoke
	public java.lang.Object loadFamily(javax.interceptor.InvocationContext context) throws Exception{
		log.info(context.getClass().getName() + "#" + context.getMethod() + "#" + context.getTarget());
		return context.proceed();
	}
	
}
