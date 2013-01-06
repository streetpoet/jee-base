package com.spstudio.love.web.producer;

import interfaces.IProductSingleton;

import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.NamingException;

import com.spstudio.love.web.qualifiers.LoveTrace;
import com.spstudio.love.web.qualifiers.ProductSingletonEJB;
import com.spstudio.love.web.system.LoveDaemon;

public class ProductSingletonEJBProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@ProductSingletonEJB
	@LoveTrace
	private IProductSingleton produce(){
		IProductSingleton products = null;
 		try {
 			LoveDaemon.ic.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
 			LoveDaemon.ic.addToEnvironment(Context.PROVIDER_URL, "jnp://localhost:1099");
 			LoveDaemon.ic.addToEnvironment(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
 			products = (IProductSingleton)LoveDaemon.ic.lookup("ProductSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return products;
	}
}
