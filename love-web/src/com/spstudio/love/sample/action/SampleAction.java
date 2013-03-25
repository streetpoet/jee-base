package com.spstudio.love.sample.action;

import interfaces.sample.ISampleSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.sample.bean.EntityQueryConversation;
import com.spstudio.love.sample.event.SampleCreateEvent;
import com.spstudio.love.sample.event.SampleCreateEventQualifier;
import com.spstudio.love.sample.event.SampleDeleteEvent;
import com.spstudio.love.sample.event.SampleDeleteEventQualifier;
import com.spstudio.love.sample.event.SampleQueryEvent;
import com.spstudio.love.sample.event.SampleQueryEvent.QueryMode;
import com.spstudio.love.sample.event.SampleQueryEventQualifier;
import com.spstudio.love.sample.event.SampleUpdateEvent;
import com.spstudio.love.sample.event.SampleUpdateEventQualifier;
import com.spstudio.love.sample.qualifier.SampleSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;

@Model
public class SampleAction {
	
	@Inject @SampleSingleRemoteBean ISampleSingleton sampleSingleton;
	@Inject @SampleCreateEventQualifier Event<SampleCreateEvent> sampleCreateEvent;
	@Inject @SampleDeleteEventQualifier Event<SampleDeleteEvent> sampleDeleteEvent;
	@Inject @SampleQueryEventQualifier Event<SampleQueryEvent> sampleQueryEvent;
	@Inject @SampleUpdateEventQualifier Event<SampleUpdateEvent> sampleUpdateEvent;
	@Inject EntityQueryConversation entityQueryConversation;
	
	public List<SelectItem> getClassifyItems() {
		List<String[]> list = sampleSingleton.retrieveSampleTypeList();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				//TODO: Add values to 'selectItems'
			}
		}
		return selectItems;
	}
	
	public void createEntity() {
		sampleCreateEvent.fire(new SampleCreateEvent());
	}
	
	public void deleteEntity() {
		sampleDeleteEvent.fire(new SampleDeleteEvent());
	}
	
	public void updateEntity(){
		sampleUpdateEvent.fire(new SampleUpdateEvent());
	}
	
	public void queryEntity() {
		sampleQueryEvent.fire(new SampleQueryEvent(QueryMode.LOAD_ALL_RECORD));
		entityQueryConversation.beginConversation();
	}

	public void loadPrePage(){
		PageObject pageObject = entityQueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		queryEntity();
	}
	
	public void loadNextPage(){
		PageObject pageObject = entityQueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		queryEntity();
	}
	
}
