package com.spstudio.love.matrix.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.helper.MatrixProjectCondition;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.constant.Configuration;
import com.spstudio.love.system.qualifier.LoveLogged;

@Named
@ConversationScoped
public class MatrixProjectQueryConversation implements Serializable {
	
	@Inject Conversation conversation;
	@Inject @LoveLogged Logger log;
	MatrixProjectCondition matrixProjectCondition;
	PageObject pageObject;
	List<MatrixProject> listMatrixProject;
	

	@PostConstruct
	public void postConstruct(){
		matrixProjectCondition = new MatrixProjectCondition();
		pageObject = new PageObject();
		log.trace("### MatrixProjectQueryConversation#postConstruct");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.trace("### MatrixProjectQueryConversation#preDestroy");
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

	public MatrixProjectCondition getMatrixProjectCondition() {
		return matrixProjectCondition;
	}

	public void setMatrixProjectCondition(MatrixProjectCondition matrixProjectCondition) {
		this.matrixProjectCondition = matrixProjectCondition;
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

	public List<MatrixProject> getListMatrixProject() {
		return listMatrixProject;
	}

	public void setListMatrixProject(List<MatrixProject> listMatrixProject) {
		this.listMatrixProject = listMatrixProject;
	}
	
}
