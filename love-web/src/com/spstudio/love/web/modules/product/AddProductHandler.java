package com.spstudio.love.web.modules.product;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.LoveLogged;
import com.spstudio.love.web.qualifiers.LoveTrace;

@Dependent
public class AddProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject
	@LoveLogged
	private Logger log;
	
	@Inject
	Product product;

	@LoveTrace
	public void addProduct(@Observes @AddProductQualifier AddProductEvent event){
		// call a specific add product handler class
		log.info("product.classifyId = " + product.getClassifyId());
	}
}
