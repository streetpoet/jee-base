package com.spstudio.love.${module.moduleName}.event;

import interfaces.I${module.moduleName?cap_first};

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.${module.moduleName}.action.${module.moduleName?cap_first}Action;
import com.spstudio.love.${module.moduleName}.bean.QueryCondition;
import com.spstudio.love.${module.moduleName}.entity.${module.moduleName?cap_first};
import com.spstudio.love.${module.moduleName}.helper.${module.moduleName?cap_first}Condition;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}Qualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class Query${module.moduleName?cap_first}Handler implements Serializable {
	
	@Inject QueryCondition queryCondition;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject ${module.moduleName?cap_first}Action ${module.moduleName}Action;
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @${module.moduleName?cap_first}Qualifier ${module.moduleName?cap_first} ${module.moduleName};

	public void query${module.moduleName?cap_first}(@Observes @Query${module.moduleName?cap_first}EventQualifier Query${module.moduleName?cap_first}Event event){
		switch (event.getQueryMode()) {
		case ALL_PRODUCTS:
			queryAll${module.moduleName?cap_first}s();
			break;
		case ONE_PRODUCT:
			loadOne${module.moduleName?cap_first}();
			break;

		default:
			break;
		}
	}
	
	private void loadOne${module.moduleName?cap_first}(){
		${module.moduleName?cap_first} ${module.moduleName} = ${module.moduleName}RemoteBean.load${module.moduleName?cap_first}(this.${module.moduleName}.getId());
		this.${module.moduleName}.set${module.moduleName?cap_first}(${module.moduleName});
	}
	
	private void queryAll${module.moduleName?cap_first}s(){
		${module.moduleName?cap_first}Condition pc = queryCondition.get${module.moduleName?cap_first}Condition();
		pc.setFamilyId(userInfo.getFamilyId());
		${module.moduleName?cap_first}Condition c = pc.clone();
		PageObject pageObject = queryCondition.getPageObject();
		IQueryResult<${module.moduleName?cap_first}> result = ${module.moduleName}RemoteBean.query${module.moduleName?cap_first}s(c, pageObject.clone());
		queryCondition.set${module.moduleName?cap_first}s(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
