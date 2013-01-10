package com.spstudio.love.product.producer;

import interfaces.IProduct;

import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.product.qualifier.ProductRemoteBean;
import com.spstudio.love.system.LoveDaemon;
import com.spstudio.love.system.qualifier.LoveTrace;

public class ProductRemoteBeanProducer {
	
	@SuppressWarnings("unused")
	@Produces
	@ProductRemoteBean
	@LoveTrace
	private IProduct produce(){
		IProduct products = null;
 		try {
 			products = (IProduct)LoveDaemon.ic.lookup("ProductBean/remote");
 		} catch (NamingException e) {
			e.printStackTrace();
		}
 		return products;
	}
}
