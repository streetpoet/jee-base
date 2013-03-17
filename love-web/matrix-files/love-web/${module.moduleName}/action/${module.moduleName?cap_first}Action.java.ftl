package com.spstudio.love.${module.moduleName}.action;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first}Singleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.${module.moduleName}.bean.${module.entityBeanName?cap_first}QueryConversation;
import com.spstudio.love.${module.moduleName}.event.Create${module.moduleName?cap_first}Event;
import com.spstudio.love.${module.moduleName}.event.Create${module.moduleName?cap_first}EventQualifier;
import com.spstudio.love.${module.moduleName}.event.Delete${module.moduleName?cap_first}Event;
import com.spstudio.love.${module.moduleName}.event.Delete${module.moduleName?cap_first}EventQualifier;
import com.spstudio.love.${module.moduleName}.event.Query${module.moduleName?cap_first}Event;
import com.spstudio.love.${module.moduleName}.event.Query${module.moduleName?cap_first}Event.QueryMode;
import com.spstudio.love.${module.moduleName}.event.Query${module.moduleName?cap_first}EventQualifier;
import com.spstudio.love.${module.moduleName}.event.Update${module.moduleName?cap_first}Event;
import com.spstudio.love.${module.moduleName}.event.Update${module.moduleName?cap_first}EventQualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}SingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.FamilyMembers;

@Model
public class ${module.moduleName?cap_first}Action {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject @${module.moduleName?cap_first}SingleRemoteBean I${module.moduleName?cap_first}Singleton ${module.moduleName}Singleton;
	@Inject @Create${module.moduleName?cap_first}EventQualifier Event<Create${module.moduleName?cap_first}Event> create${module.moduleName?cap_first}Event;
	@Inject @Delete${module.moduleName?cap_first}EventQualifier Event<Delete${module.moduleName?cap_first}Event> delete${module.moduleName?cap_first}Event;
	@Inject @Query${module.moduleName?cap_first}EventQualifier Event<Query${module.moduleName?cap_first}Event> query${module.moduleName?cap_first}Event;
	@Inject @Update${module.moduleName?cap_first}EventQualifier Event<Update${module.moduleName?cap_first}Event> update${module.moduleName?cap_first}Event;
	@Inject @FamilyMembers List<UserInfo> members;
	@Inject ${module.entityBeanName?cap_first}QueryConversation ${module.entityBeanName}QueryConversation;
	
	public List<SelectItem> getClassifyItems() {
		List<String[]> list = ${module.moduleName}Singleton.${module.singletonEjbMethodName}();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				//TODO: Add values to 'selectItems'
			}
		}
		return selectItems;
	}
	
	public void create${module.entityBeanName?cap_first}() {
		create${module.moduleName?cap_first}Event.fire(new Create${module.moduleName?cap_first}Event());
	}
	
	public void delete${module.entityBeanName?cap_first}() {
		delete${module.moduleName?cap_first}Event.fire(new Delete${module.moduleName?cap_first}Event());
	}
	
	public void update${module.entityBeanName?cap_first}(){
		update${module.moduleName?cap_first}Event.fire(new Update${module.moduleName?cap_first}Event());
	}
	
	public void query${module.entityBeanName?cap_first}() {
		query${module.moduleName?cap_first}Event.fire(new Query${module.moduleName?cap_first}Event(QueryMode.LOAD_ALL_RECORD));
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
