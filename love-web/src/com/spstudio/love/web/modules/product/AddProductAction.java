package com.spstudio.love.web.modules.product;

import interfaces.IProductSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.web.modules.main.UserInfo;
import com.spstudio.love.web.qualifiers.product.ProductSingleRemoteBean;
import com.spstudio.love.web.qualifiers.system.FamilyMembers;
import com.spstudio.love.web.qualifiers.system.LoveLogged;

@Model
public class AddProductAction {
	
	@Inject @ProductSingleRemoteBean IProductSingleton productSingleton;
	@Inject @AddProductQualifier Event<AddProductEvent> addProductEvent;
	@Inject @FamilyMembers List<UserInfo> members;
	@Inject @LoveLogged Logger log;
	
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
	
	public void addProduct(){
		addProductEvent.fire(new AddProductEvent());
	}
}
