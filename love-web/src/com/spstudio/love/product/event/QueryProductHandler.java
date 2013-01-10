package com.spstudio.love.product.event;

import interfaces.IProduct;
import interfaces.IQueryResult;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.entity.ProductCondition;
import com.spstudio.love.product.qualifier.ProductRemoteBean;
import com.spstudio.love.system.entity.UserInfo;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.LoveTrace;

@Dependent
public class QueryProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject @com.spstudio.love.product.qualifier.ProductCondition ProductCondition condition;
	@Inject @ProductRemoteBean IProduct productRemoteBean;
	@Inject UserInfo userInfo;
	@Inject @LoveLogged Logger log;

	@LoveTrace
	public void queryProduct(@Observes @AddProductEventQualifier QueryProductEvent event){
		
		condition.setFamilyId(userInfo.getFamilyId());
		ProductCondition c = condition.clone();
		IQueryResult<Product> result = productRemoteBean.queryProducts(c, null);
		log.info("result count: " + result.getResultData().size());
		condition.clear();
	}
}
