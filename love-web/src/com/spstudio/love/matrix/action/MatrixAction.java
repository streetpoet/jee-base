package com.spstudio.love.matrix.action;

import interfaces.matrix.IMatrix;
import interfaces.matrix.IMatrixSingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.bean.MatrixProjectQueryConversation;
import com.spstudio.love.matrix.entity.MatrixFunction;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.event.MatrixCreateEvent;
import com.spstudio.love.matrix.event.MatrixCreateEvent.MatrixCreateMode;
import com.spstudio.love.matrix.event.MatrixCreateEventQualifier;
import com.spstudio.love.matrix.event.MatrixDeleteEvent;
import com.spstudio.love.matrix.event.MatrixDeleteEventQualifier;
import com.spstudio.love.matrix.event.MatrixQueryEvent;
import com.spstudio.love.matrix.event.MatrixQueryEvent.QueryMode;
import com.spstudio.love.matrix.event.MatrixQueryEventQualifier;
import com.spstudio.love.matrix.event.MatrixUpdateEvent;
import com.spstudio.love.matrix.event.MatrixUpdateEventQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.LoveTrace;

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
	@Inject @LoveLogged Logger log;
	
	private int selectedProjectId;
	private int selectedModuleId;
	private int selectedFunctionId;
	
	public void onMatrixProjectListValueChange(ValueChangeEvent event){
		selectedProjectId = (Integer)event.getNewValue();
	}
	
	public void onMatrixModuleListValueChange(ValueChangeEvent event){
		selectedModuleId = (Integer)event.getNewValue();
	}
	
	public void onMatrixFunctionListValueChange(ValueChangeEvent event){
		selectedFunctionId = (Integer)event.getNewValue();
	}
	
	@LoveTrace
	public void startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}
	
	@LoveTrace
	public void endConversation() {
		if (!(conversation.isTransient())) {
			conversation.end();
		}
	}
	
	public void createMatrixSolution() {
		matrixCreateEvent.fire(new MatrixCreateEvent(MatrixCreateMode.SOLUTION));
	}
	
	public void createMatrixProject() {
		matrixCreateEvent.fire(new MatrixCreateEvent(MatrixCreateMode.PROJECT));
	}
	
	public void createMatrixModule(){
		matrixCreateEvent.fire(new MatrixCreateEvent(MatrixCreateMode.MODULE));
	}
	
	@LoveTrace
	public void createMatrixFunction(){
		matrixCreateEvent.fire(new MatrixCreateEvent(MatrixCreateMode.FUNCTION));
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
	
	/*
	 * Html Selection
	 */
	
	public List<SelectItem> getMatrixProjectList() {
		List<MatrixProject> list = matrixSingleton.retrieveProjectList();
		List<SelectItem> matrixProjectList = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (MatrixProject project: list){
				matrixProjectList.add(new SelectItem(project.getId(), project.getProjectName()));
			}
		}
		return matrixProjectList;
	}
	
	public List<SelectItem> getMatrixModuleList() {
		List<SelectItem> matrixModuleList = new ArrayList<SelectItem>();
		if (selectedProjectId != -1){
			List<MatrixModule> list = matrixBean.loadMatrixModuleList(selectedProjectId);
			if (list != null && list.size() != 0){
				for (MatrixModule module: list){
					matrixModuleList.add(new SelectItem(module.getId(), module.getModuleName()));
				}
			}
		}
		return matrixModuleList;
	}
	
	public List<SelectItem> getMatrixFunctionList() {
		List<SelectItem> matrixFunctionList = new ArrayList<SelectItem>();
		if (selectedModuleId != -1){
			List<MatrixFunction> list = matrixBean.loadMatrixFunctionList(selectedModuleId);
			if (list != null && list.size() != 0){
				for (MatrixFunction func: list){
					matrixFunctionList.add(new SelectItem(func.getId(), func.getFunctionName()));
				}
			}
		}
		return matrixFunctionList;
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

	/*
	 * Getter/Setter 
	 */
	
	public int getSelectedProjectId() {
		return selectedProjectId;
	}

	public void setSelectedProjectId(int selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}

	public int getSelectedModuleId() {
		return selectedModuleId;
	}

	public void setSelectedModuleId(int selectedModuleId) {
		this.selectedModuleId = selectedModuleId;
	}

	public int getSelectedFunctionId() {
		return selectedFunctionId;
	}

	public void setSelectedFunctionId(int selectedFunctionId) {
		this.selectedFunctionId = selectedFunctionId;
	}
	
}
