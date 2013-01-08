package com.spstudio.love.web.producer;

import interfaces.IProduct;

import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.NamingException;

import com.spstudio.love.web.qualifiers.LoveTrace;
import com.spstudio.love.web.qualifiers.ProductRemoteBean;
import com.spstudio.love.web.system.LoveDaemon;

public class ProductRemoteBeanProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@ProductRemoteBean
	@LoveTrace
	private IProduct produce(){
		IProduct products = null;
 		try {
 			LoveDaemon.ic.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
 			LoveDaemon.ic.addToEnvironment(Context.PROVIDER_URL, "jnp://localhost:1099");
 			LoveDaemon.ic.addToEnvironment(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
 			products = (IProduct)LoveDaemon.ic.lookup("ProductBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return products;
	}
}
