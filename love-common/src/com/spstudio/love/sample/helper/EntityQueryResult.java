package com.spstudio.love.sample.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.sample.entity.Entity;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class EntityQueryResult implements IQueryResult<Entity>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -25634428554850L;
	
	private List<Entity> listEntity;
	private PageObject pageObject;
	
	@Override
	public List<Entity> getResultData() {
		return listEntity;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListEntity(List<Entity> listEntity) {
		this.listEntity = listEntity;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
