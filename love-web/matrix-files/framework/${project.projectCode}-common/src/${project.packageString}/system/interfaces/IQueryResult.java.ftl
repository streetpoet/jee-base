package ${project.packageString}.system。interfaces;

import java.util.List;

import ${project.packageString}.system.bean.PageObject;

public interface IQueryResult<K> {
	public List<K> getResultData();
	public PageObject getPageObject();
}
