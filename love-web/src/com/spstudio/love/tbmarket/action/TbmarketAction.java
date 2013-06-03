package com.spstudio.love.tbmarket.action;

import interfaces.tbmarket.ITbmarketSingleton;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.tbmarket.event.TbmarketCreateEvent;
import com.spstudio.love.tbmarket.event.TbmarketCreateEventQualifier;
import com.spstudio.love.tbmarket.event.TbmarketDeleteEvent;
import com.spstudio.love.tbmarket.event.TbmarketDeleteEventQualifier;
import com.spstudio.love.tbmarket.event.TbmarketQueryEvent;
import com.spstudio.love.tbmarket.event.TbmarketQueryEvent.QueryMode;
import com.spstudio.love.tbmarket.event.TbmarketQueryEventQualifier;
import com.spstudio.love.tbmarket.event.TbmarketUpdateEvent;
import com.spstudio.love.tbmarket.event.TbmarketUpdateEventQualifier;
import com.spstudio.love.tbmarket.qualifier.TbmarketSingleRemoteBean;

import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.LoveTrace;

@ConversationScoped
@Named
public class TbmarketAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -155145312477688L;
	
	@Inject Conversation conversation;
	@Inject @TbmarketSingleRemoteBean ITbmarketSingleton tbmarketSingleton;
	@Inject @TbmarketCreateEventQualifier Event<TbmarketCreateEvent> tbmarketCreateEvent;
	@Inject @TbmarketDeleteEventQualifier Event<TbmarketDeleteEvent> tbmarketDeleteEvent;
	@Inject @TbmarketQueryEventQualifier Event<TbmarketQueryEvent> tbmarketQueryEvent;
	@Inject @TbmarketUpdateEventQualifier Event<TbmarketUpdateEvent> tbmarketUpdateEvent;
	@Inject @LoveLogged Logger log;
	private PageObject pageObject;
	
	public List<SelectItem> getTbAcountTypeBeanList() {
		return null;
	}
	
	public void createTbAcountBean() {
		tbmarketCreateEvent.fire(new TbmarketCreateEvent());
	}
	
	public void deleteTbAcountBean() {
		tbmarketDeleteEvent.fire(new TbmarketDeleteEvent());
	}
	
	public void updateTbAcountBean(){
		tbmarketUpdateEvent.fire(new TbmarketUpdateEvent());
	}
	
	public void queryTbAcountBean() {
		tbmarketQueryEvent.fire(new TbmarketQueryEvent(QueryMode.LOAD_ALL_RECORD));
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
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		queryTbAcountBean();
	}
	
	public void loadNextPage(){
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		queryTbAcountBean();
	}
	
}
