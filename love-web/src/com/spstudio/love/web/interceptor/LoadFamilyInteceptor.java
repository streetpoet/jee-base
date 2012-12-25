package com.spstudio.love.web.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;

import com.spstudio.love.web.qualifiers.LoadFamilyInterceptor;

@Interceptor
@LoadFamilyInterceptor
public class LoadFamilyInteceptor {
	
	@AroundInvoke
	public java.lang.Object loadFamily(javax.interceptor.InvocationContext context) throws Exception{
		return context.proceed();
	}
	
}
