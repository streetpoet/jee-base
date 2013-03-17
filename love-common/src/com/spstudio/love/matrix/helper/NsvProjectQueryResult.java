package com.spstudio.love.matrix.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.matrix.entity.NsvProject;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class NsvProjectQueryResult implements IQueryResult<NsvProject>, Serializable {
	
	private List<NsvProject> listNsvProject;
	private PageObject pageObject;
	
	@Override
	public List<NsvProject> getResultData() {
		return listNsvProject;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListNsvProject(List<NsvProject> listNsvProject) {
		this.listNsvProject = listNsvProject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
