package com.spstudio.love.${module.moduleName}.helper;

import java.io.Serializable;
import java.util.List;

import com.spstudio.love.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

public class ${module.entityBeanName?cap_first}QueryResult implements IQueryResult<${module.entityBeanName?cap_first}>, Serializable {
	
	private List<${module.entityBeanName?cap_first}> list${module.entityBeanName?cap_first};
	private PageObject pageObject;
	
	@Override
	public List<${module.entityBeanName?cap_first}> getResultData() {
		return list${module.entityBeanName?cap_first};
	}

	@Override
	public PageObject getPageObject() {
		return pageObject;
	}

	public void setList${module.entityBeanName?cap_first}(List<${module.entityBeanName?cap_first}> list${module.entityBeanName?cap_first}) {
		this.list${module.entityBeanName?cap_first} = list${module.entityBeanName?cap_first};
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

}
