package interfaces.matrix;

import javax.ejb.Remote;

import com.spstudio.love.matrix.entity.${module.entityBeanName?cap_first};
import com.spstudio.love.matrix.helper.${module.entityBeanName?cap_first}Condition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.interfaces.IQueryResult;

@Remote
public interface IMatrix {
	
	public boolean create${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});
	
	public int delete${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});
	
	public int update${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName});

	public IQueryResult<${module.entityBeanName?cap_first}> query${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first}Condition condition, PageObject pageObject);
	
	public ${module.entityBeanName?cap_first} load${module.entityBeanName?cap_first}(int ${module.entityBeanName}Id);

}