package com.spstudio.love.${module.moduleName}.action;

import interfaces.${module.moduleName}.I${module.firstUpperModuleName}Singleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.${module.moduleName}.bean.QueryCondition;
import com.spstudio.love.${module.moduleName}.event.create${module.firstUpperModuleName}Event;
import com.spstudio.love.${module.moduleName}.event.create${module.firstUpperModuleName}EventQualifier;
import com.spstudio.love.${module.moduleName}.event.Query${module.firstUpperModuleName}Event;
import com.spstudio.love.${module.moduleName}.event.Query${module.firstUpperModuleName}Event.QueryMode;
import com.spstudio.love.${module.moduleName}.event.Query${module.firstUpperModuleName}EventQualifier;
import com.spstudio.love.${module.moduleName}.event.Update${module.firstUpperModuleName}Event;
import com.spstudio.love.${module.moduleName}.event.Update${module.firstUpperModuleName}EventQualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}SingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.FamilyMembers;

@Model
public class ${module.firstUpperModuleName}Action {
	
	@Inject @${module.firstUpperModuleName}SingleRemoteBean I${module.firstUpperModuleName}Singleton ${module.moduleName}Singleton;
	@Inject @Create${module.firstUpperModuleName}EventQualifier Event<Create${module.firstUpperModuleName}Event> create${module.firstUpperModuleName}Event;
	@Inject @Delete${module.firstUpperModuleName}EventQualifier Event<Delete${module.firstUpperModuleName}Event> delete${module.firstUpperModuleName}Event;
	@Inject @Query${module.firstUpperModuleName}EventQualifier Event<Query${module.firstUpperModuleName}Event> query${module.firstUpperModuleName}Event;
	@Inject @Update${module.firstUpperModuleName}EventQualifier Event<Update${module.firstUpperModuleName}Event> update${module.firstUpperModuleName}Event;
	@Inject @FamilyMembers List<UserInfo> members;
	@Inject QueryCondition queryCondition;
	
	public List<SelectItem> getClassifyItems() {
		List<String[]> list = null;
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				
			}
		}
		return selectItems;
	}
	
	public List<SelectItem> getFamilyMembers() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (members != null && members.size() != 0){
			for (UserInfo user: members){
				selectItems.add(new SelectItem(user.getUserId(), user.getNickName()));
			}
		}
		return selectItems;
	}
	
	public void create${module.firstUpperModuleName}() {
		create${module.firstUpperModuleName}Event.fire(new create${module.firstUpperModuleName}Event());
	}
	
	public void delete${module.firstUpperModuleName}() {
		delete${module.firstUpperModuleName}Event.fire(new delete${module.firstUpperModuleName}Event());
	}
	
	public void update${module.firstUpperModuleName}(){
		update${module.firstUpperModuleName}Event.fire(new Update${module.firstUpperModuleName}Event());
	}
	
	public void query${module.firstUpperModuleName}() {
		query${module.firstUpperModuleName}Event.fire(new Query${module.firstUpperModuleName}Event(QueryMode.ALL_PRODUCTS));
		queryCondition.beginConversation();
	}

	public void loadPrePage(){
		PageObject pageObject = queryCondition.getPageObject();
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		query${module.firstUpperModuleName}();
	}
	
	public void loadNextPage(){
		PageObject pageObject = queryCondition.getPageObject();
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		query${module.firstUpperModuleName}();
	}
	
	public void beforePhaseFor${module.firstUpperModuleName}(javax.faces.event.PhaseEvent event){
		if (event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			if (!FacesContext.getCurrentInstance().isPostback()){
				query${module.firstUpperModuleName}Event.fire(new Query${module.firstUpperModuleName}Event(QueryMode.ONE_PRODUCT));
				queryCondition.endConversation();
			}
		}
	}
	
}
