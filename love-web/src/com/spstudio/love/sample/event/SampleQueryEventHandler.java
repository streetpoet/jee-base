package com.spstudio.love.sample.event;

import interfaces.sample.ISample;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.sample.action.SampleAction;
import com.spstudio.love.sample.bean.EntityQueryConversation;
import com.spstudio.love.sample.entity.Entity;
import com.spstudio.love.sample.helper.EntityCondition;
import com.spstudio.love.sample.qualifier.EntityQualifier;
import com.spstudio.love.sample.qualifier.SampleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class SampleQueryEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -152857786856626L;
	
	@Inject EntityQueryConversation entityQueryConversation;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject SampleAction sampleAction;
	@Inject @SampleRemoteBean ISample sampleRemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @EntityQualifier Entity entity;

	public void queryEntity(@Observes @SampleQueryEventQualifier SampleQueryEvent event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_RECORD:
			loadAllSample();
			break;
		case LOAD_SINGLE_RECORD:
			loadSingleSample();
			break;

		default:
			break;
		}
	}
	
	private void loadSingleSample(){
		Entity entity = sampleRemoteBean.loadEntity(this.entity.getId());
		this.entity.setEntity(entity);
	}
	
	private void loadAllSample(){
		EntityCondition entityCondition = entityQueryConversation.getEntityCondition();
		EntityCondition tempEntityCondition = entityCondition.clone();
		PageObject pageObject = entityQueryConversation.getPageObject();
		IQueryResult<Entity> result = sampleRemoteBean.queryEntity(tempEntityCondition, pageObject.clone());
		entityQueryConversation.setListEntity(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
