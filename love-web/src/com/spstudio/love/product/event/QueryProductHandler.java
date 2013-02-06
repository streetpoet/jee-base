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
import com.spstudio.love.product.qualifier.ProductQualifier;
import com.spstudio.love.product.qualifier.ProductRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class QueryProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject QueryCondition queryCondition;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject ProductAction productAction;
	@Inject @ProductRemoteBean IProduct productRemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @ProductQualifier Product product;

	public void queryProduct(@Observes @QueryProductEventQualifier QueryProductEvent event){
		switch (event.getQueryMode()) {
		case ALL_PRODUCTS:
			queryAllProducts();
			break;
		case ONE_PRODUCT:
			loadOneProduct();
			break;

		default:
			break;
		}
	}
	
	private void loadOneProduct(){
		Product product = productRemoteBean.loadProduct(this.product.getId());
		this.product.setProduct(product);
	}
	
	private void queryAllProducts(){
		ProductCondition pc = queryCondition.getProductCondition();
		pc.setFamilyId(userInfo.getFamilyId());
		ProductCondition c = pc.clone();
		PageObject pageObject = queryCondition.getPageObject();
		IQueryResult<Product> result = productRemoteBean.queryProducts(c, pageObject.clone());
		queryCondition.setProducts(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
