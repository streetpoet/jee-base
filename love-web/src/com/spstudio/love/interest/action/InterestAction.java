package com.spstudio.love.interest.action;

import interfaces.interest.IInterestSingleton;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.interest.bean.TechSelectBeanQueryConversation;
import com.spstudio.love.interest.bean.TechTypeBeanHtmlSelectionBean;
import com.spstudio.love.interest.event.InterestCreateEvent;
import com.spstudio.love.interest.event.InterestCreateEventQualifier;
import com.spstudio.love.interest.event.InterestDeleteEvent;
import com.spstudio.love.interest.event.InterestDeleteEventQualifier;
import com.spstudio.love.interest.event.InterestQueryEvent;
import com.spstudio.love.interest.event.InterestQueryEvent.QueryMode;
import com.spstudio.love.interest.event.InterestQueryEventQualifier;
import com.spstudio.love.interest.event.InterestUpdateEvent;
import com.spstudio.love.interest.event.InterestUpdateEventQualifier;
import com.spstudio.love.interest.qualifier.InterestSingleRemoteBean;

import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.LoveTrace;

@ConversationScoped
@Named
public class InterestAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -199409220084664L;
	
	@Inject Conversation conversation;
	@Inject @InterestSingleRemoteBean IInterestSingleton interestSingleton;
	@Inject @InterestCreateEventQualifier Event<InterestCreateEvent> interestCreateEvent;
	@Inject @InterestDeleteEventQualifier Event<InterestDeleteEvent> interestDeleteEvent;
	@Inject @InterestQueryEventQualifier Event<InterestQueryEvent> interestQueryEvent;
	@Inject @InterestUpdateEventQualifier Event<InterestUpdateEvent> interestUpdateEvent;
	@Inject TechSelectBeanQueryConversation techSelectBeanQueryConversation;
	@Inject TechTypeBeanHtmlSelectionBean techTypeBeanHtmlSelectBean;
	@Inject @LoveLogged Logger log;
	
	public List<SelectItem> getTechTypeBeanList() {
		return techTypeBeanHtmlSelectBean.getTechTypeBeanList();
	}
	
	public void createTechSelectBean() {
		interestCreateEvent.fire(new InterestCreateEvent());
	}
	
	public void deleteTechSelectBean() {
		interestDeleteEvent.fire(new InterestDeleteEvent());
	}
	
	public void updateTechSelectBean(){
		interestUpdateEvent.fire(new InterestUpdateEvent());
	}
	
	public void queryTechSelectBean() {
		interestQueryEvent.fire(new InterestQueryEvent(QueryMode.LOAD_ALL_RECORD));
		techSelectBeanQueryConversation.beginConversation();
	}
	
	@LoveTrace
	public void startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	@LoveTrace
	public void endConversation() {
		if (!(conversation.isTransient())) {
			conversation.end();
		}
	}

	public void loadPrePage(){
		PageObject pageObject = techSelectBeanQueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		queryTechSelectBean();
	}
	
	public void loadNextPage(){
		PageObject pageObject = techSelectBeanQueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		queryTechSelectBean();
	}
	
}