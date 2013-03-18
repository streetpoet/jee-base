package com.spstudio.love.matrix.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class MatrixModuleQueryResult implements IQueryResult<MatrixModule>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -165651322663385L;
	
	private List<MatrixModule> listMatrixModule;
	private PageObject pageObject;
	
	@Override
	public List<MatrixModule> getResultData() {
		return listMatrixModule;
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setListMatrixModule(List<MatrixModule> listMatrixModule) {
		this.listMatrixModule = listMatrixModule;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
