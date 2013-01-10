package com.spstudio.love.system.interfaces;

import java.util.List;

import com.spstudio.love.system.bean.PageObject;

public interface IQueryResult<K> {
	public List<K> getResultData();
	public PageObject getPageObject();
}
