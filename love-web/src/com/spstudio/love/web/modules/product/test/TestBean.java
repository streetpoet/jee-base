package com.spstudio.love.web.modules.product.test;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.web.qualifiers.LoveLogged;
import com.spstudio.love.web.qualifiers.LoveTrace;

@ConversationScoped
@Named
public class TestBean implements Serializable{
	
	@Inject
	Conversation conversation;
	
	@Inject 
	@LoveLogged 
	Logger log;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7126889773771390446L;

	@LoveTrace
	public void print(){
		log.info("cid = " + conversation.getId() + " | " + conversation.isTransient());
	}

	public void begin(){
		conversation.begin();
		conversation.setTimeout(10L);
	}
	
	public void end(){
		conversation.end();
	}
	
	@PostConstruct
	public void postConstruct(){
		log.info("TestBean#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info("TestBean#preDestroy");
	}
}
