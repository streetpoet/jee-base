package com.spstudio.love.${module.moduleName}.action;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first}Singleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.${module.moduleName}.bean.${module.entityBeanName?cap_first}QueryConversation;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}CreateEvent;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}CreateEventQualifier;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}DeleteEvent;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}DeleteEventQualifier;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}QueryEvent;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}QueryEvent.QueryMode;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}QueryEventQualifier;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}UpdateEvent;
import com.spstudio.love.${module.moduleName}.event.${module.moduleName?cap_first}UpdateEventQualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}SingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;

@Model
public class ${module.moduleName?cap_first}Action {
	
	@Inject @${module.moduleName?cap_first}SingleRemoteBean I${module.moduleName?cap_first}Singleton ${module.moduleName}Singleton;
	@Inject @${module.moduleName?cap_first}CreateEventQualifier Event<${module.moduleName?cap_first}CreateEvent> ${module.moduleName}CreateEvent;
	@Inject @${module.moduleName?cap_first}DeleteEventQualifier Event<${module.moduleName?cap_first}DeleteEvent> ${module.moduleName}DeleteEvent;
	@Inject @${module.moduleName?cap_first}QueryEventQualifier Event<${module.moduleName?cap_first}QueryEvent> ${module.moduleName}QueryEvent;
	@Inject @${module.moduleName?cap_first}UpdateEventQualifier Event<${module.moduleName?cap_first}UpdateEvent> ${module.moduleName}UpdateEvent;
	@Inject ${module.entityBeanName?cap_first}QueryConversation ${module.entityBeanName}QueryConversation;
	@Inject ${module.moduleName?cap_first}${module.selectBeanName?cap_first}HtmlSelectionBean ${module.moduleName}${module.selectBeanName?cap_first}HtmlSelectBean;
	
	public List<SelectItem> get${module.selectBeanName?cap_first}List() {
		return ${module.moduleName}${module.selectBeanName?cap_first}HtmlSelectBean.get${module.selectBeanName?cap_first}List;
	}
	
	public void create${module.entityBeanName?cap_first}() {
		${module.moduleName}CreateEvent.fire(new ${module.moduleName?cap_first}CreateEvent());
	}
	
	public void delete${module.entityBeanName?cap_first}() {
		${module.moduleName}DeleteEvent.fire(new ${module.moduleName?cap_first}DeleteEvent());
	}
	
	public void update${module.entityBeanName?cap_first}(){
		${module.moduleName}UpdateEvent.fire(new ${module.moduleName?cap_first}UpdateEvent());
	}
	
	public void query${module.entityBeanName?cap_first}() {
		${module.moduleName}QueryEvent.fire(new ${module.moduleName?cap_first}QueryEvent(QueryMode.LOAD_ALL_RECORD));
		${module.entityBeanName}QueryConversation.beginConversation();
	}

	public void loadPrePage(){
		PageObject pageObject = ${module.entityBeanName}QueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		query${module.entityBeanName?cap_first}();
	}
	
	public void loadNextPage(){
		PageObject pageObject = ${module.entityBeanName}QueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		query${module.entityBeanName?cap_first}();
	}
	
}
