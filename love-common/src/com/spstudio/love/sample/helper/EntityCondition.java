package com.spstudio.love.sample.helper;

import com.spstudio.love.sample.entity.Entity;

public class EntityCondition extends Entity implements Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -61313706875787L;
	
	public EntityCondition clone(){
		return (EntityCondition)super.clone();
	}
}
