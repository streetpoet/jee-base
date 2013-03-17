package com.spstudio.love.matrix.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class MatrixProjectQueryResult implements IQueryResult<MatrixProject>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -828211811134L;
	
	private List<MatrixProject> listMatrixProject;
	private PageObject pageObject;
	
	@Override
	public List<MatrixProject> getResultData() {
		return listMatrixProject;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListMatrixProject(List<MatrixProject> listMatrixProject) {
		this.listMatrixProject = listMatrixProject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
