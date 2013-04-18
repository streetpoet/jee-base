package com.spstudio.love.interest.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class TechSelectBeanQueryResult implements IQueryResult<TechSelectBean>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -23010606024340L;
	
	private List<TechSelectBean> listTechSelectBean;
	private PageObject pageObject;
	
	@Override
	public List<TechSelectBean> getResultData() {
		return listTechSelectBean;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListTechSelectBean(List<TechSelectBean> listTechSelectBean) {
		this.listTechSelectBean = listTechSelectBean;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
