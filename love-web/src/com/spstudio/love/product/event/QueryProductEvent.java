package com.spstudio.love.product.event;

import java.io.Serializable;

public class QueryProductEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6431452248872549535L;

	public QueryProductEvent(QueryMode mode){
		queryMode = mode;
	}
	
	private QueryMode queryMode;

	public QueryMode getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(QueryMode queryMode) {
		this.queryMode = queryMode;
	}

	public static enum QueryMode{
		ALL_PRODUCTS, ONE_PRODUCT;
	}

}
