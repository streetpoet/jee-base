package com.spstudio.love.matrix.action;

import interfaces.matrix.IMatrixSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.matrix.bean.MatrixProjectQueryConversation;
import com.spstudio.love.matrix.event.MatrixCreateEvent;
import com.spstudio.love.matrix.event.MatrixCreateEventQualifier;
import com.spstudio.love.matrix.event.MatrixDeleteEvent;
import com.spstudio.love.matrix.event.MatrixDeleteEventQualifier;
import com.spstudio.love.matrix.event.MatrixQueryEvent;
import com.spstudio.love.matrix.event.MatrixQueryEvent.QueryMode;
import com.spstudio.love.matrix.event.MatrixQueryEventQualifier;
import com.spstudio.love.matrix.event.MatrixUpdateEvent;
import com.spstudio.love.matrix.event.MatrixUpdateEventQualifier;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.FamilyMembers;

@Model
public class MatrixAction {
	
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingleton;
	@Inject @MatrixCreateEventQualifier Event<MatrixCreateEvent> matrixCreateEvent;
	@Inject @MatrixDeleteEventQualifier Event<MatrixDeleteEvent> matrixDeleteEvent;
	@Inject @MatrixQueryEventQualifier Event<MatrixQueryEvent> matrixQueryEvent;
	@Inject @MatrixUpdateEventQualifier Event<MatrixUpdateEvent> matrixUpdateEvent;
	@Inject @FamilyMembers List<UserInfo> members;
	@Inject MatrixProjectQueryConversation matrixProjectQueryConversation;
	
	public List<SelectItem> getClassifyItems() {
		List<String[]> list = matrixSingleton.retrieveProjectList();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				//TODO: Add values to 'selectItems'
			}
		}
		return selectItems;
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
