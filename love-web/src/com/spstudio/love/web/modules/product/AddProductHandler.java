package com.spstudio.love.web.modules.product;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.LoveLogged;

@RequestScoped
public class AddProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject
	private Product product;

	@LoveLogged
	public void addProduct(@Observes @AddProductQualifier AddProductEvent event){
		// call a specific add product handler class
		Logger.getLogger(AddProductHandler.class).info("product.classifyId = " + product.getClassifyId());
	}
}
