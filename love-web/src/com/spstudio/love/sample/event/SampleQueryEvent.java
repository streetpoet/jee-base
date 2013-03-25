package com.spstudio.love.sample.event;

import java.io.Serializable;

public class SampleQueryEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -85318369772031L;

	public SampleQueryEvent(QueryMode mode){
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
