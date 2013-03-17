package com.spstudio.love.${module.moduleName}.event;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.${module.moduleName}.action.${module.moduleName?cap_first}Action;
import com.spstudio.love.${module.moduleName}.bean.${module.moduleName?cap_first}ProjectQueryConversation;
import com.spstudio.love.${module.moduleName}.entity.${module.moduleName?cap_first}Project;
import com.spstudio.love.${module.moduleName}.helper.${module.moduleName?cap_first}ProjectCondition;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}ProjectQualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class Query${module.moduleName?cap_first}Handler implements Serializable {
	
	@Inject ${module.moduleName?cap_first}ProjectQueryConversation ${module.moduleName}ProjectQueryConversation;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject ${module.moduleName?cap_first}Action ${module.moduleName}Action;
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @${module.moduleName?cap_first}ProjectQualifier ${module.moduleName?cap_first}Project ${module.moduleName}Project;

	public void query${module.moduleName?cap_first}(@Observes @Query${module.moduleName?cap_first}EventQualifier Query${module.moduleName?cap_first}Event event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_MATRIXPROJECT:
			loadAll${module.moduleName?cap_first}();
			break;
		case LOAD_ONE_MATRIXPROJECT:
			loadOne${module.moduleName?cap_first}();
			break;

		default:
			break;
		}
	}
	
	private void loadOne${module.moduleName?cap_first}(){
		${module.moduleName?cap_first}Project ${module.moduleName}Project = ${module.moduleName}RemoteBean.load${module.moduleName?cap_first}Project(this.${module.moduleName}Project.getId());
		this.${module.moduleName}Project.set${module.moduleName?cap_first}Project(${module.moduleName}Project);
	}
	
	private void loadAll${module.moduleName?cap_first}(){
		${module.moduleName?cap_first}ProjectCondition ${module.moduleName}ProjectCondition = ${module.moduleName}ProjectQueryConversation.get${module.moduleName?cap_first}ProjectCondition();
		${module.moduleName?cap_first}ProjectCondition temp${module.moduleName?cap_first}ProjectCondition = ${module.moduleName}ProjectCondition.clone();
		PageObject pageObject = ${module.moduleName}ProjectQueryConversation.getPageObject();
		IQueryResult<${module.moduleName?cap_first}Project> result = ${module.moduleName}RemoteBean.query${module.moduleName?cap_first}Project(temp${module.moduleName?cap_first}ProjectCondition, pageObject.clone());
		${module.moduleName}ProjectQueryConversation.setList${module.moduleName?cap_first}Project(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
