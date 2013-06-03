package com.spstudio.love.tbmarket.event;

import interfaces.tbmarket.ITbmarket;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.tbmarket.action.TbmarketAction;
import com.spstudio.love.tbmarket.entity.TbAcountBean;
import com.spstudio.love.tbmarket.helper.TbAcountBeanCondition;
import com.spstudio.love.tbmarket.qualifier.TbAcountBeanQualifier;
import com.spstudio.love.tbmarket.qualifier.TbmarketRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class TbmarketQueryEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -176326166744276L;
	
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject TbmarketAction tbmarketAction;
	@Inject @TbmarketRemoteBean ITbmarket tbmarketRemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @TbAcountBeanQualifier TbAcountBean tbAcountBean;

	public void queryTbAcountBean(@Observes @TbmarketQueryEventQualifier TbmarketQueryEvent event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_RECORD:
			loadAllTbmarket();
			break;
		case LOAD_SINGLE_RECORD:
			loadSingleTbmarket();
			break;

		default:
			break;
		}
	}
	
	private void loadSingleTbmarket(){
		TbAcountBean tbAcountBean = tbmarketRemoteBean.loadTbAcountBean(this.tbAcountBean.getId());
		this.tbAcountBean.setTbAcountBean(tbAcountBean);
	}
	
	private void loadAllTbmarket(){
		//TbAcountBeanCondition tbAcountBeanCondition = tbAcountBeanQueryConversation.getTbAcountBeanCondition();
		//TbAcountBeanCondition tempTbAcountBeanCondition = tbAcountBeanCondition.clone();
		//PageObject pageObject = tbAcountBeanQueryConversation.getPageObject();
		//IQueryResult<TbAcountBean> result = tbmarketRemoteBean.queryTbAcountBean(tempTbAcountBeanCondition, pageObject.clone());
		//tbAcountBeanQueryConversation.setListTbAcountBean(result.getResultData());
		
		// set paging object
		//pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		//pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		//pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
