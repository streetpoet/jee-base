package com.spstudio.love.interest.event;

import java.io.Serializable;

public class InterestQueryEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -224380789876315L;

	public InterestQueryEvent(QueryMode mode){
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
		LOAD_ALL_RECORD, 
		LOAD_SINGLE_RECORD, 
		LOAD_LIKED_TECH_LIST, 
		LOAD_CLASSIFY_STAT_TECH_LIST,
		LOAD_MEMBER_STAT_TECH_LIST,
		LOAD_CHART_CLASSIFY_PERCENTAGE,
	}

}
