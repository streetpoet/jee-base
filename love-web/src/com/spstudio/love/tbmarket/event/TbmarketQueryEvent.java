package com.spstudio.love.tbmarket.event;

import java.io.Serializable;

public class TbmarketQueryEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -219214319538837L;

	public TbmarketQueryEvent(QueryMode mode){
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
		LOAD_ALL_RECORD, LOAD_SINGLE_RECORD;
	}

}
