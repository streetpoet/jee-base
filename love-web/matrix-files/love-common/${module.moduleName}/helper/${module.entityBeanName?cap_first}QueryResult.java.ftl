package ${project.packageString}.${module.moduleName}.helper;

import java.io.Serializable;
import java.util.List;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.system.bean.PageObject;
import ${project.packageString}.system.interfaces.IQueryResult;

public class ${module.entityBeanName?cap_first}QueryResult implements IQueryResult<${module.entityBeanName?cap_first}>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
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
