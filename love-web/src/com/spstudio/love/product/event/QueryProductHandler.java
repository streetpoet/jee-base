package com.spstudio.love.product.event;

import interfaces.IProduct;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.product.action.ProductAction;
import com.spstudio.love.product.bean.QueryCondition;
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
	
	@Inject QueryCondition queryCondition;
	@Inject UserInfo userInfo;
	@Inject ProductAction productAction;
	@Inject @ProductRemoteBean IProduct productRemoteBean;
	@Inject @LoveLogged Logger log;

	@LoveTrace
	public void queryProduct(@Observes @QueryProductEventQualifier QueryProductEvent event){
		ProductCondition pc = queryCondition.getProductCondition();
		pc.setFamilyId(userInfo.getFamilyId());
		ProductCondition c = pc.clone();
		PageObject pageObject = queryCondition.getPageObject();
		IQueryResult<Product> result = productRemoteBean.queryProducts(c, pageObject.clone());
		productAction.setProducts(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));
	}
}
