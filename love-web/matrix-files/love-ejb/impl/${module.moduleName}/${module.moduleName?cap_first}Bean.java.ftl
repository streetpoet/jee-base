package impl.${module.moduleName};

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.system.helper.DatabaseHelper;

@Stateless
public class ${module.moduleName?cap_first}Bean implements I${module.moduleName?cap_first} {

	@Inject DatabaseHelper helper;
	
	@Override
	public boolean create${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}) {
		Object[] params = new Object[]{
		};
		return helper.doDMLOperation("", params) == 1;
	}
	
	@Override
	public int delete${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}) {
		Object[] params = new Object[]{
		};
		return helper.doDMLOperation("", params);
	}
	
	@Override
	public int update${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first} ${module.entityBeanName}) {
		Object[] params = new Object[]{
		};
		return helper.doDMLOperation("", params);
	}

	@Override
	public IQueryResult<${module.entityBeanName?cap_first}> query${module.entityBeanName?cap_first}(${module.entityBeanName?cap_first}Condition condition,
			PageObject pageObject) {
		IQueryResult<${module.entityBeanName?cap_first}> returnResult = new ${module.entityBeanName?cap_first}QueryResult();
		
		// query total number
		List<Object[]> result = helper.doQuery("", null);
		if (result != null && result.size() != 0){
			pageObject.setTotalRecordsNumber(0);
		}
		((${module.entityBeanName?cap_first}QueryResult)returnResult).setPageObject(pageObject);
		
		// query paging data
		Object[] params = new Object[] {
				pageObject.getOffset(),
				pageObject.getMaxRecordsPerPage()
			};
		result = helper.doQuery("", params);
		List<${module.entityBeanName?cap_first}> listProduct = new ArrayList<${module.entityBeanName?cap_first}>();
		if (result != null && result.size() != 0){
			${module.entityBeanName?cap_first} temp${module.entityBeanName} = new ${module.entityBeanName?cap_first}();
			for(Object[] row: result){
				${module.entityBeanName?cap_first} ${module.entityBeanName} = temp${module.entityBeanName}.clone();
//				product.setId((Integer)row[0]);
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
