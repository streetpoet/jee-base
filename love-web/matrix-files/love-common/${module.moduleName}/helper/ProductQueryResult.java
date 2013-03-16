package com.spstudio.love.product.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class ProductQueryResult implements IQueryResult<Product>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4999126268651424923L;
	
	private List<Product> listProduct;
	private PageObject pageObject;
	
	@Override
	public List<Product> getResultData() {
		return listProduct;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
