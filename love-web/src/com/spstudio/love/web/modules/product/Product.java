package com.spstudio.love.web.modules.product;

import javax.enterprise.inject.Model;

@Model
public class Product {
	private int classifyId;
	private int productName;
	
	public int getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}
	public int getProductName() {
		return productName;
	}
	public void setProductName(int productName) {
		this.productName = productName;
	}
}
