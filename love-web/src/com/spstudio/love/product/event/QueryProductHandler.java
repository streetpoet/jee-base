package com.spstudio.love.product.event;

import interfaces.IProduct;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.product.action.ProductAction;
import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.helper.ProductCondition;
import com.spstudio.love.product.qualifier.ProductRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.entity.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
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
	@Inject ProductAction productAction;
	@Inject PageObject pageObject;
	

	@LoveTrace
	public void queryProduct(@Observes @QueryProductEventQualifier QueryProductEvent event){
		
		condition.setFamilyId(userInfo.getFamilyId());
		ProductCondition c = condition.clone();
		IQueryResult<Product> result = productRemoteBean.queryProducts(c, pageObject);
		productAction.setProducts(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getTotalRecordsNumber() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage());
		
		condition.clear();
	}
}
