package com.spstudio.love.matrix.event;

import java.io.Serializable;

public class MatrixQueryEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -210041685571803L;

	public MatrixQueryEvent(QueryMode mode){
		queryMode = mode;
	}
	
	private QueryMode queryMode;

	public QueryMode getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(QueryMode queryMode) {
		this.queryMode = queryMode;
	}

	public static enum QueryMode {
		LOAD_ALL_RECORD, LOAD_SINGLE_RECORD, LOAD_SINGLE_MODULE, LOAD_SINGLE_FUNCTION, LOAD_SINGLE_PROJECT;
	}

}
