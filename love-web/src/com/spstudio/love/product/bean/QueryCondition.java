package com.spstudio.love.product.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.spstudio.love.product.helper.ProductCondition;

@Named
@ConversationScoped
public class QueryCondition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5758972403494728013L;
	
	@Inject Conversation conversation;
	ProductCondition productCondition;
	
	@PostConstruct
	public void postConstruct(){
		productCondition = new ProductCondition();
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("### QueryCondition will destroy.");
	}

	public void begin(){
		if (conversation.isTransient()){
			System.out.println("### isTransient, begin");
			conversation.begin();
			conversation.setTimeout(5000L);
		}else{
			System.out.println("### not isTransient");
		}
	}
	
	public void end(){
		conversation.end();
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
	
	
}
