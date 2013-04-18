package com.spstudio.love.interest.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.helper.TechSelectBeanCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.constant.Configuration;
import com.spstudio.love.system.qualifier.LoveLogged;

@Named
@ConversationScoped
public class TechSelectBeanQueryConversation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -10195720398038L;
	
	@Inject Conversation conversation;
	@Inject @LoveLogged Logger log;
	TechSelectBeanCondition techSelectBeanCondition;
	PageObject pageObject;
	List<TechSelectBean> listTechSelectBean;
	

	@PostConstruct
	public void postConstruct(){
		techSelectBeanCondition = new TechSelectBeanCondition();
		pageObject = new PageObject();
		log.trace("### TechSelectBeanQueryConversation#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.trace("### TechSelectBeanQueryConversation#preDestroy");
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

	public TechSelectBeanCondition getTechSelectBeanCondition() {
		return techSelectBeanCondition;
	}

	public void setTechSelectBeanCondition(TechSelectBeanCondition techSelectBeanCondition) {
		this.techSelectBeanCondition = techSelectBeanCondition;
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

	public List<TechSelectBean> getListTechSelectBean() {
		return listTechSelectBean;
	}

	public void setListTechSelectBean(List<TechSelectBean> listTechSelectBean) {
		this.listTechSelectBean = listTechSelectBean;
	}
	
}
