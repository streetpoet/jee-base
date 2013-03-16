package com.spstudio.love.${module.moduleName}.event;

import java.io.Serializable;

public class Query${module.moduleName?cap_first}Event implements Serializable{


	public Query${module.moduleName?cap_first}Event(QueryMode mode){
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
