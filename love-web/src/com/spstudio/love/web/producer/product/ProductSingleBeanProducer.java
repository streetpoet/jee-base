package com.spstudio.love.web.producer.product;

import interfaces.IProductSingleton;

import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.web.qualifiers.product.ProductSingleRemoteBean;
import com.spstudio.love.web.system.LoveDaemon;

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
