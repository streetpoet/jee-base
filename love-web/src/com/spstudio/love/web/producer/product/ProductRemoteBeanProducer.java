package com.spstudio.love.web.producer.product;

import interfaces.IProduct;

import javax.enterprise.inject.Produces;
import javax.naming.NamingException;

import com.spstudio.love.web.qualifiers.product.ProductRemoteBean;
import com.spstudio.love.web.qualifiers.system.LoveTrace;
import com.spstudio.love.web.system.LoveDaemon;

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
