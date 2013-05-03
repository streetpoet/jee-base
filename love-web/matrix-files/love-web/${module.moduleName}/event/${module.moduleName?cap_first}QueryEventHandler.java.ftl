package ${project.packageString}.${module.moduleName}.event;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ${project.packageString}.${module.moduleName}.action.${module.moduleName?cap_first}Action;
import ${project.packageString}.${module.moduleName}.bean.${module.entityBeanName?cap_first}QueryConversation;
import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.${module.moduleName}.helper.${module.entityBeanName?cap_first}Condition;
import ${project.packageString}.${module.moduleName}.qualifier.${module.entityBeanName?cap_first}Qualifier;
import ${project.packageString}.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import ${project.packageString}.system.bean.PageObject;
import ${project.packageString}.system.bean.UserInfo;
import ${project.packageString}.system.interfaces.IQueryResult;
import ${project.packageString}.system.qualifier.LoveLogged;
import ${project.packageString}.system.qualifier.UserInfoQualifier;

@Dependent
public class ${module.moduleName?cap_first}QueryEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject ${module.entityBeanName?cap_first}QueryConversation ${module.entityBeanName}QueryConversation;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject ${module.moduleName?cap_first}Action ${module.moduleName}Action;
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @${module.entityBeanName?cap_first}Qualifier ${module.entityBeanName?cap_first} ${module.entityBeanName};

	public void query${module.entityBeanName?cap_first}(@Observes @${module.moduleName?cap_first}QueryEventQualifier ${module.moduleName?cap_first}QueryEvent event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_RECORD:
			loadAll${module.moduleName?cap_first}();
			break;
		case LOAD_SINGLE_RECORD:
			loadSingle${module.moduleName?cap_first}();
			break;

		default:
			break;
		}
	}
	
	private void loadSingle${module.moduleName?cap_first}(){
		${module.entityBeanName?cap_first} ${module.entityBeanName} = ${module.moduleName}RemoteBean.load${module.entityBeanName?cap_first}(this.${module.entityBeanName}.getId());
		this.${module.entityBeanName}.set${module.entityBeanName?cap_first}(${module.entityBeanName});
	}
	
	private void loadAll${module.moduleName?cap_first}(){
		${module.entityBeanName?cap_first}Condition ${module.entityBeanName}Condition = ${module.entityBeanName}QueryConversation.get${module.entityBeanName?cap_first}Condition();
		${module.entityBeanName?cap_first}Condition temp${module.entityBeanName?cap_first}Condition = ${module.entityBeanName}Condition.clone();
		PageObject pageObject = ${module.entityBeanName}QueryConversation.getPageObject();
		IQueryResult<${module.entityBeanName?cap_first}> result = ${module.moduleName}RemoteBean.query${module.entityBeanName?cap_first}(temp${module.entityBeanName?cap_first}Condition, pageObject.clone());
		${module.entityBeanName}QueryConversation.setList${module.entityBeanName?cap_first}(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
