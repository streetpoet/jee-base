package com.spstudio.love.product.producer;

import interfaces.IProductSingleton;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.product.qualifier.ProductSingleRemoteBean;
import com.spstudio.love.system.LoveDaemon;
import com.spstudio.love.system.qualifier.LoveTrace;

public class ProductSingleBeanProducer {
	
	@EJB
	LoveDaemon loveDaemon;
	
	@SuppressWarnings("unused")
	@Produces
	@ProductSingleRemoteBean
	@LoveTrace
	private IProductSingleton produce(){
		IProductSingleton products = null;
 		try {
 			products = (IProductSingleton)loveDaemon.getInitialContext().lookup("ProductSingletonBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return products;
	}
}
