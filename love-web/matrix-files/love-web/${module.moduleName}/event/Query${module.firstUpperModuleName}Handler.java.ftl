package com.spstudio.love.${module.moduleName}.event;

import interfaces.I${module.firstUpperModuleName};

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.${module.moduleName}.action.${module.firstUpperModuleName}Action;
import com.spstudio.love.${module.moduleName}.bean.QueryCondition;
import com.spstudio.love.${module.moduleName}.entity.${module.firstUpperModuleName};
import com.spstudio.love.${module.moduleName}.helper.${module.firstUpperModuleName}Condition;
import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}Qualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}RemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class Query${module.firstUpperModuleName}Handler implements Serializable {
	
	@Inject QueryCondition queryCondition;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject ${module.firstUpperModuleName}Action ${module.moduleName}Action;
	@Inject @${module.firstUpperModuleName}RemoteBean I${module.firstUpperModuleName} ${module.moduleName}RemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @${module.firstUpperModuleName}Qualifier ${module.firstUpperModuleName} ${module.moduleName};

	public void query${module.firstUpperModuleName}(@Observes @Query${module.firstUpperModuleName}EventQualifier Query${module.firstUpperModuleName}Event event){
		switch (event.getQueryMode()) {
		case ALL_PRODUCTS:
			queryAll${module.firstUpperModuleName}s();
			break;
		case ONE_PRODUCT:
			loadOne${module.firstUpperModuleName}();
			break;

		default:
			break;
		}
	}
	
	private void loadOne${module.firstUpperModuleName}(){
		${module.firstUpperModuleName} ${module.moduleName} = ${module.moduleName}RemoteBean.load${module.firstUpperModuleName}(this.${module.moduleName}.getId());
		this.${module.moduleName}.set${module.firstUpperModuleName}(${module.moduleName});
	}
	
	private void queryAll${module.firstUpperModuleName}s(){
		${module.firstUpperModuleName}Condition pc = queryCondition.get${module.firstUpperModuleName}Condition();
		pc.setFamilyId(userInfo.getFamilyId());
		${module.firstUpperModuleName}Condition c = pc.clone();
		PageObject pageObject = queryCondition.getPageObject();
		IQueryResult<${module.firstUpperModuleName}> result = ${module.moduleName}RemoteBean.query${module.firstUpperModuleName}s(c, pageObject.clone());
		queryCondition.set${module.firstUpperModuleName}s(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
