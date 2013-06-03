package com.spstudio.love.tbmarket.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.tbmarket.entity.TbAcountBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class TbAcountBeanQueryResult implements IQueryResult<TbAcountBean>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -259749603474112L;
	
	private List<TbAcountBean> listTbAcountBean;
	private PageObject pageObject;
	
	@Override
	public List<TbAcountBean> getResultData() {
		return listTbAcountBean;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListTbAcountBean(List<TbAcountBean> listTbAcountBean) {
		this.listTbAcountBean = listTbAcountBean;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
