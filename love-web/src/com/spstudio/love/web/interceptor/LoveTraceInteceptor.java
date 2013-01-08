package com.spstudio.love.web.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.system.LoveTrace;

@Interceptor
@LoveTrace
public class LoveTraceInteceptor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7061648997621546160L;
	
	Logger log = Logger.getLogger(LoveTraceInteceptor.class);
	
	@AroundInvoke
	public java.lang.Object invoke(javax.interceptor.InvocationContext context) throws Exception{
		Method m = context.getMethod();
		log.trace("Entering method: " + m.getDeclaringClass().getName() + "#" + m.getName());
		return context.proceed();
	}
	
}
