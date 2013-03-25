package com.spstudio.love.sample.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.sample.entity.Entity;
import com.spstudio.love.sample.helper.EntityCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.constant.Configuration;
import com.spstudio.love.system.qualifier.LoveLogged;

@Named
@ConversationScoped
public class EntityQueryConversation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -39999534203007L;
	
	@Inject Conversation conversation;
	@Inject @LoveLogged Logger log;
	EntityCondition entityCondition;
	PageObject pageObject;
	List<Entity> listEntity;
	

	@PostConstruct
	public void postConstruct(){
		entityCondition = new EntityCondition();
		pageObject = new PageObject();
		log.trace("### EntityQueryConversation#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.trace("### EntityQueryConversation#preDestroy");
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

	public EntityCondition getEntityCondition() {
		return entityCondition;
	}

	public void setEntityCondition(EntityCondition entityCondition) {
		this.entityCondition = entityCondition;
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

	public List<Entity> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<Entity> listEntity) {
		this.listEntity = listEntity;
	}
	
}
