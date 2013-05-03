package impl.${module.moduleName};

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.${module.moduleName}.helper.${module.entityBeanName?cap_first}Condition;
import ${project.packageString}.${module.moduleName}.helper.${module.entityBeanName?cap_first}QueryResult;
import ${project.packageString}.system.bean.PageObject;
import ${project.packageString}.system.helper.DatabaseHelper;
import ${project.packageString}.system.interfaces.IQueryResult;

@Stateless
public class ${module.moduleName?cap_first}Bean implements I${module.moduleName?cap_first} {

	@Inject DatabaseHelper helper;
	@Resource EJBContext context;
	
	@Override
	public boolean create${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params) == 1; //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int delete${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}
	
	@Override
	public int update${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}) {
		Object[] params = new Object[]{
			//TODO: Add parameters here.
		};
		return helper.doDMLOperation("", params); //TODO: Replace "" with proper sql.
	}

	@Override
	public IQueryResult<${module.entityBeanName?cap_first}> query${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first}Condition condition,
			PageObject pageObject) {
		IQueryResult<${module.entityBeanName?cap_first}> returnResult = new ${module.entityBeanName?cap_first}QueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery("", null); //TODO: Replace "" with proper sql.
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber(0);
		}
		((${module.entityBeanName?cap_first}QueryResult)returnResult).setPageObject(pageObject);
		
		// query paging data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery("", params); //TODO: Replace "" with proper sql.
		List<${module.entityBeanName?cap_first}> list${module.entityBeanName?cap_first} = new ArrayList<${module.entityBeanName?cap_first}>();
		if (result != null && result.size() != 0){
			${module.entityBeanName?cap_first} temp${module.entityBeanName?cap_first} = new ${module.entityBeanName?cap_first}();
			for(Object[] row: result){
				${module.entityBeanName?cap_first} ${module.entityBeanName} = temp${module.entityBeanName?cap_first}.clone();
//				${module.entityBeanName}.setId((Integer)row[0]);
				list${module.entityBeanName?cap_first}.add(${module.entityBeanName});
			}
		}
		((${module.entityBeanName?cap_first}QueryResult)returnResult).setList${module.entityBeanName?cap_first}(list${module.entityBeanName?cap_first});
		
		return returnResult;
	}
	
	/**
	 * Return ${module.entityBeanName?cap_first} Object
	 */
	@Override
	public ${module.entityBeanName?cap_first} load${module.entityBeanName?cap_first}(int ${module.entityBeanName}Id) {
		Object[] params = new Object[]{${module.entityBeanName}Id};
		List<Object[]> result = helper.doQuery("", params);
		if (result != null && result.size() != 0){
			Object[] row = result.get(0);
			${module.entityBeanName?cap_first} ${module.entityBeanName} = new ${module.entityBeanName?cap_first}();
//			${module.entityBeanName}.setId((Integer)row[0]);
			return ${module.entityBeanName};
		}
		return null;
	}
}
