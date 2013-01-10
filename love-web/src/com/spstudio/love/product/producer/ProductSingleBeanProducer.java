package com.spstudio.love.product.producer;

import interfaces.IProductSingleton;

import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.product.qualifier.ProductSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;
import com.spstudio.love.system.qualifier.LoveTrace;

public class ProductSingleBeanProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@ProductSingleRemoteBean
	@LoveTrace
	private IProductSingleton produce(){
		IProductSingleton products = null;
 		try {
 			products = (IProductSingleton)LoveDaemon.ic.lookup("ProductSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return products;
	}
}
