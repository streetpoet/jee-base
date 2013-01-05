package com.spstudio.love.web.modules.product;

import interfaces.IProductSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.spstudio.love.web.qualifiers.ProductSingletonEJB;

@RequestScoped
@Named
public class AddProductAction {
	
	@Inject
	@ProductSingletonEJB
	private IProductSingleton productSingleton;
	
	@Inject
	@AddProductQualifier
	private Event<AddProductEvent> addProductEvent;
	
	public List<SelectItem> getClassifyItems(){
		List<String[]> list = productSingleton.retrieveProductClassify();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				selectItems.add(new SelectItem(data[IProductSingleton.INDEX_CLASSIFY_ID], data[IProductSingleton.INDEX_CLASSIFY_NAME]));
			}
		}
		return selectItems;
	}
	
	public void addProduct(){
		addProductEvent.fire(new AddProductEvent());
	}
}
