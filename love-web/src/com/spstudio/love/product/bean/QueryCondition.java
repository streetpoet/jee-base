package com.spstudio.love.product.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.helper.ProductCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.constant.Configuration;
import com.spstudio.love.system.qualifier.LoveLogged;

@Named
@ConversationScoped
public class QueryCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5758972403494728013L;
	
	@Inject Conversation conversation;
	@Inject @LoveLogged Logger log;
	ProductCondition productCondition;
	PageObject pageObject;
	List<Product> products;
	

	@PostConstruct
	public void postConstruct(){
		productCondition = new ProductCondition();
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

	public ProductCondition getProductCondition() {
		return productCondition;
	}

	public void setProductCondition(ProductCondition productCondition) {
		this.productCondition = productCondition;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
