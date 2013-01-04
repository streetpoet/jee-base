package com.spstudio.love.web.producer;

import interfaces.IProducts;

import javax.enterprise.inject.Produces;
import javax.naming.Context;
import javax.naming.NamingException;

import com.spstudio.love.web.qualifiers.LoveLogged;
import com.spstudio.love.web.qualifiers.ProductsEJB;
import com.spstudio.love.web.system.LoveDaemon;

public class ProductsEJBProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@ProductsEJB
	@LoveLogged
	private IProducts produce(){
		IProducts products = null;
 		try {
 			LoveDaemon.ic.addToEnvironment(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
 			LoveDaemon.ic.addToEnvironment(Context.PROVIDER_URL, "jnp://localhost:1099");
 			LoveDaemon.ic.addToEnvironment(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
 			products = (IProducts)LoveDaemon.ic.lookup("ProductsBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return products;
	}
}
