package com.spstudio.love.matrix.event;

import java.io.Serializable;

public class QueryMatrixEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -154431318579705L;

	public QueryMatrixEvent(QueryMode mode){
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
