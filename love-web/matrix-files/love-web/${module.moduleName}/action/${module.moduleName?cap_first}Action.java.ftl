package ${project.packageString}.${module.moduleName}.action;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first}Singleton;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}CreateEvent;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}CreateEventQualifier;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}DeleteEvent;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}DeleteEventQualifier;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}QueryEvent;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}QueryEvent.QueryMode;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}QueryEventQualifier;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}UpdateEvent;
import ${project.packageString}.${module.moduleName}.event.${module.moduleName?cap_first}UpdateEventQualifier;
import ${project.packageString}.${module.moduleName}.qualifier.${module.moduleName?cap_first}SingleRemoteBean;

import ${project.packageString}.system.bean.PageObject;
import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Logged;
import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Trace;

@ConversationScoped
@Named
public class ${module.moduleName?cap_first}Action implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject Conversation conversation;
	@Inject @${module.moduleName?cap_first}SingleRemoteBean I${module.moduleName?cap_first}Singleton ${module.moduleName}Singleton;
	@Inject @${module.moduleName?cap_first}CreateEventQualifier Event<${module.moduleName?cap_first}CreateEvent> ${module.moduleName}CreateEvent;
	@Inject @${module.moduleName?cap_first}DeleteEventQualifier Event<${module.moduleName?cap_first}DeleteEvent> ${module.moduleName}DeleteEvent;
	@Inject @${module.moduleName?cap_first}QueryEventQualifier Event<${module.moduleName?cap_first}QueryEvent> ${module.moduleName}QueryEvent;
	@Inject @${module.moduleName?cap_first}UpdateEventQualifier Event<${module.moduleName?cap_first}UpdateEvent> ${module.moduleName}UpdateEvent;
	@Inject ${module.entityBeanName?cap_first}QueryConversation ${module.entityBeanName}QueryConversation;
	@Inject @${project.projectCode?cap_first}Logged Logger log;
	private PageObject pageObject;
	
	public List<SelectItem> get${module.selectBeanName?cap_first}List() {
		return null;
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
	}
	
	@${project.projectCode?cap_first}Trace
	public void startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	@${project.projectCode?cap_first}Trace
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
		query${module.entityBeanName?cap_first}();
	}
	
	public void loadNextPage(){
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		query${module.entityBeanName?cap_first}();
	}
	
}
