package ${project.packageString}.${module.moduleName}.helper;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};

public class ${module.entityBeanName?cap_first}Condition extends ${module.entityBeanName?cap_first} implements Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	public ${module.entityBeanName?cap_first}Condition clone(){
		return (${module.entityBeanName?cap_first}Condition)super.clone();
	}
}
