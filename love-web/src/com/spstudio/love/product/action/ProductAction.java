package com.spstudio.love.product.action;

import interfaces.IProductSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.product.bean.QueryCondition;
import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.event.AddProductEvent;
import com.spstudio.love.product.event.AddProductEventQualifier;
import com.spstudio.love.product.event.QueryProductEvent;
import com.spstudio.love.product.event.QueryProductEventQualifier;
import com.spstudio.love.product.qualifier.ProductSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.entity.UserInfo;
import com.spstudio.love.system.qualifier.FamilyMembers;
import com.spstudio.love.system.qualifier.LoveLogged;

@Model
public class ProductAction {
	
	@Inject @ProductSingleRemoteBean IProductSingleton productSingleton;
	@Inject @AddProductEventQualifier Event<AddProductEvent> addProductEvent;
	@Inject @QueryProductEventQualifier Event<QueryProductEvent> queryProductEvent;
	@Inject @FamilyMembers List<UserInfo> members;
	@Inject QueryCondition queryCondition;
	
	private List<Product> products;
	
	public List<SelectItem> getClassifyItems() {
		List<String[]> list = productSingleton.retrieveProductClassify();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				selectItems.add(new SelectItem(data[IProductSingleton.INDEX_CLASSIFY_ID], data[IProductSingleton.INDEX_CLASSIFY_NAME]));
			}
		}
		return selectItems;
	}
	
	public List<SelectItem> getFamilyMembers() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (members != null && members.size() != 0){
			for (UserInfo user: members){
				selectItems.add(new SelectItem(user.getUserId(), user.getNickName()));
			}
		}
		return selectItems;
	}
	
	/**
	 * Add New Product
	 */
	public void addProduct() {
		addProductEvent.fire(new AddProductEvent());
	}
	
	@LoveLogged
	public void queryProduct() {
		queryProductEvent.fire(new QueryProductEvent());
		queryCondition.begin();
	}

	public void loadPrePage(){
		PageObject pageObject = queryCondition.getPageObject();
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		queryProduct();
	}
	
	public void loadNextPage(){
		PageObject pageObject = queryCondition.getPageObject();
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		queryProduct();
	}
	
	public void beforePhase(javax.faces.event.PhaseEvent event){
		if (event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			if (!FacesContext.getCurrentInstance().isPostback()){
				queryProduct();
			}
		}
	}
	
	/*
	 * Getter/Setter
	 */
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
