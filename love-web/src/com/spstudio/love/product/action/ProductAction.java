package com.spstudio.love.product.action;

import interfaces.IProductSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostAddToViewEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

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
	@Inject PageObject pageObject;
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
	
	public void addProduct() {
		addProductEvent.fire(new AddProductEvent());
	}
	
	@LoveLogged
	public void queryProduct() {
		queryProductEvent.fire(new QueryProductEvent());
	}

	public void loadPrePage(){
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		queryProduct();
	}
	
	public void loadNextPage(){
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		queryProduct();
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
