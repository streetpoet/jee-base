package interfaces.${module.moduleName};

import javax.ejb.Remote;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.${module.moduleName}.helper.${module.entityBeanName?cap_first}Condition;
import ${project.packageString}.system.bean.PageObject;
import ${project.packageString}.system.interfaces.IQueryResult;

@Remote
public interface I${module.moduleName?cap_first} {
	
	public boolean create${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});
	
	public int delete${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});
	
	public int update${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});

	public IQueryResult<${module.entityBeanName?cap_first}> query${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first}Condition condition, PageObject pageObject);
	
	public ${module.entityBeanName?cap_first} load${module.entityBeanName?cap_first}(int ${module.entityBeanName}Id);

}