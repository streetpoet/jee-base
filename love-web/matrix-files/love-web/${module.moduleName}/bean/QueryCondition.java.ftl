package com.spstudio.love.${module.moduleName}.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.${module.moduleName}.entity.${module.moduleName?cap_first};
import com.spstudio.love.${module.moduleName}.helper.${module.moduleName?cap_first}Condition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.constant.Configuration;
import com.spstudio.love.system.qualifier.LoveLogged;

@Named
@ConversationScoped
public class QueryCondition implements Serializable {
	
	@Inject Conversation conversation;
	@Inject @LoveLogged Logger log;
	${module.moduleName?cap_first}Condition ${module.moduleName}Condition;
	PageObject pageObject;
	List<${module.moduleName?cap_first}> ${module.moduleName}s;
	

	@PostConstruct
	public void postConstruct(){
		${module.moduleName}Condition = new ${module.moduleName?cap_first}Condition();
		pageObject = new PageObject();
		log.trace("### QueryCondition#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.trace("### QueryCondition#preDestroy");
	}

	public void beginConversation(){
		if (conversation.isTransient()){
			conversation.begin();
			conversation.setTimeout(Configuration.CONVERSATION_TIMEOUT);
		}
	}
	
	public void endConversation(){
		if (!conversation.isTransient()){
			conversation.end();
		}
	}

	public ${module.moduleName?cap_first}Condition get${module.moduleName?cap_first}Condition() {
		return ${module.moduleName}Condition;
	}

	public void set${module.moduleName?cap_first}Condition(${module.moduleName?cap_first}Condition ${module.moduleName}Condition) {
		this.${module.moduleName}Condition = ${module.moduleName}Condition;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public PageObject getPageObject() {
		return pageObject;
	}

	public void setPageObject(PageObject pageObject) {
		this.pageObject = pageObject;
	}

	public List<${module.moduleName?cap_first}> get${module.moduleName?cap_first}s() {
		return ${module.moduleName}s;
	}

	public void set${module.moduleName?cap_first}s(List<${module.moduleName?cap_first}> ${module.moduleName}s) {
		this.${module.moduleName}s = ${module.moduleName}s;
	}
	
}
