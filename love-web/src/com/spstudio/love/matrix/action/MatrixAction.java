package com.spstudio.love.matrix.action;

import interfaces.matrix.IMatrix;
import interfaces.matrix.IMatrixSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

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
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;

@Model
public class MatrixAction {
	
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingleton;
	@Inject @MatrixRemoteBean IMatrix matrixBean;
	@Inject @MatrixCreateEventQualifier Event<MatrixCreateEvent> matrixCreateEvent;
	@Inject @MatrixDeleteEventQualifier Event<MatrixDeleteEvent> matrixDeleteEvent;
	@Inject @MatrixQueryEventQualifier Event<MatrixQueryEvent> matrixQueryEvent;
	@Inject @MatrixUpdateEventQualifier Event<MatrixUpdateEvent> matrixUpdateEvent;
	@Inject MatrixProjectQueryConversation matrixProjectQueryConversation;
	@Inject @com.spstudio.love.matrix.qualifier.MatrixProjectQualifier MatrixProject matrixProject;
	@Inject @com.spstudio.love.matrix.qualifier.MatrixModuleQualifier MatrixModule matrixModule;
	
	private List<SelectItem> matrixProjectSelectItem;
	private List<SelectItem> matrixModuleSelectItem;
	
	public List<SelectItem> getMatrixProjectList() {

		if (matrixProjectSelectItem != null){
			return matrixProjectSelectItem;
		}
		List<MatrixProject> list = matrixSingleton.retrieveProjectList();
		matrixProjectSelectItem = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (MatrixProject project: list){
				matrixProjectSelectItem.add(new SelectItem(project.getId(), project.getProjectName()));
			}
		}
		return matrixProjectSelectItem;
	}
	
	public List<SelectItem> getMatrixModuleList(){
		if (matrixModuleSelectItem != null){
			return matrixModuleSelectItem;
		}
		matrixModuleSelectItem = new ArrayList<SelectItem>();
		if (matrixProject.getId() != -1){
			List<MatrixModule> list = matrixBean.loadMatrixModuleList(matrixProject.getId());
			if (list != null && list.size() != 0){
				for (MatrixModule module: list){
					matrixModuleSelectItem.add(new SelectItem(module.getId(), module.getModuleName()));
				}
			}
		}
		return matrixModuleSelectItem;
	}
	
	public void onMatrixProjectListValueChange(ValueChangeEvent event){
		matrixProject.setId((Integer)event.getNewValue());
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
