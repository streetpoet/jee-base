package interfaces.${module.moduleName};

import javax.ejb.Remote;

@Remote
public interface I${module.moduleName?cap_first} {
		
	@Inject DatabaseHelper helper;
	
	@Override
	public boolean create${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});
	
	@Override
	public int delete${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});
	
	@Override
	public int update${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});

	@Override
	public IQueryResult<${module.entityBeanName?cap_first}> query${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first}Condition condition, PageObject pageObject);
	
	@Override
	public ${module.entityBeanName?cap_first} load${module.entityBeanName?cap_first}(int ${module.entityBeanName}Id);

}