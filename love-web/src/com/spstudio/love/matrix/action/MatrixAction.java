package com.spstudio.love.matrix.action;

import interfaces.matrix.IMatrix;
import interfaces.matrix.IMatrixSingleton;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.bean.MatrixModuleHtmlSelectionBean;
import com.spstudio.love.matrix.bean.MatrixProjectHtmlSelectionBean;
import com.spstudio.love.matrix.bean.MatrixProjectQueryConversation;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.event.MatrixCreateEvent;
import com.spstudio.love.matrix.event.MatrixCreateEventQualifier;
import com.spstudio.love.matrix.event.MatrixDeleteEvent;
import com.spstudio.love.matrix.event.MatrixDeleteEventQualifier;
import com.spstudio.love.matrix.event.MatrixQueryEvent;
import com.spstudio.love.matrix.event.MatrixQueryEvent.QueryMode;
import com.spstudio.love.matrix.event.MatrixQueryEventQualifier;
import com.spstudio.love.matrix.event.MatrixUpdateEvent;
import com.spstudio.love.matrix.event.MatrixUpdateEventQualifier;
import com.spstudio.love.matrix.qualifier.MatrixModuleQualifier;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.qualifier.LoveLogged;

@ConversationScoped
@Named
public class MatrixAction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7032744584511809741L;
	
	@Inject Conversation conversation;
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingleton;
	@Inject @MatrixRemoteBean IMatrix matrixBean;
	@Inject @MatrixCreateEventQualifier Event<MatrixCreateEvent> matrixCreateEvent;
	@Inject @MatrixDeleteEventQualifier Event<MatrixDeleteEvent> matrixDeleteEvent;
	@Inject @MatrixQueryEventQualifier Event<MatrixQueryEvent> matrixQueryEvent;
	@Inject @MatrixUpdateEventQualifier Event<MatrixUpdateEvent> matrixUpdateEvent;
	@Inject MatrixProjectQueryConversation matrixProjectQueryConversation;
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;
	@Inject @MatrixModuleQualifier MatrixModule matrixModule;
	@Inject MatrixModuleHtmlSelectionBean matrixModuleHtmlSelectBean;
	@Inject MatrixProjectHtmlSelectionBean matrixProjectHtmlSelectionBean;
	@Inject @LoveLogged Logger log;
	
	public List<SelectItem> getMatrixProjectList() {
		return matrixProjectHtmlSelectionBean.getMatrixProjectList();
	}
	
	public List<SelectItem> getMatrixModuleList(){
		return matrixModuleHtmlSelectBean.getMatrixModuleList();
	}
	
	public void onMatrixProjectListValueChange(ValueChangeEvent event){
		log.trace("event.getOldValue() = " + event.getOldValue());
		matrixModuleHtmlSelectBean.reloadModleListByProjectId((Integer)event.getNewValue());
	}
	
	public void startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	public void endConversation() {
		if (!(conversation.isTransient())) {
			log.trace("--------------conversation end: cid=" + conversation.getId());
			conversation.end();
		}
	}
	
	public void createMatrixProject() {
		matrixCreateEvent.fire(new MatrixCreateEvent());
	}
	
	public void deleteMatrixProject() {
		matrixDeleteEvent.fire(new MatrixDeleteEvent());
	}
	
	public void updateMatrixProject(){
		matrixUpdateEvent.fire(new MatrixUpdateEvent());
	}
	
	public void queryMatrixProject() {
		matrixQueryEvent.fire(new MatrixQueryEvent(QueryMode.LOAD_ALL_RECORD));
		matrixProjectQueryConversation.beginConversation();
	}

	public void loadPrePage(){
		PageObject pageObject = matrixProjectQueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() > 1){
			int offset = pageObject.getOffset() - pageObject.getMaxRecordsPerPage();
			pageObject.setOffset(offset < 0 ? 0 : offset);
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() - 1);
		}
		queryMatrixProject();
	}
	
	public void loadNextPage(){
		PageObject pageObject = matrixProjectQueryConversation.getPageObject();
		if (pageObject.getCurrentPageNumber() < pageObject.getMaxPageNumber()){
			pageObject.setOffset(pageObject.getOffset() + pageObject.getMaxRecordsPerPage());
			pageObject.setCurrentPageNumber(pageObject.getCurrentPageNumber() + 1);
		}
		queryMatrixProject();
	}
	
}
