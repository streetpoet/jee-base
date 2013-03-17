package com.spstudio.love.matrix.action;

import interfaces.matrix.IMatrixSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.matrix.bean.MatrixProjectQueryConversation;
import com.spstudio.love.matrix.event.CreateMatrixEvent;
import com.spstudio.love.matrix.event.CreateMatrixEventQualifier;
import com.spstudio.love.matrix.event.DeleteMatrixEvent;
import com.spstudio.love.matrix.event.DeleteMatrixEventQualifier;
import com.spstudio.love.matrix.event.QueryMatrixEvent;
import com.spstudio.love.matrix.event.QueryMatrixEvent.QueryMode;
import com.spstudio.love.matrix.event.QueryMatrixEventQualifier;
import com.spstudio.love.matrix.event.UpdateMatrixEvent;
import com.spstudio.love.matrix.event.UpdateMatrixEventQualifier;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.FamilyMembers;

@Model
public class MatrixAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -161126299956366L;
	
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingleton;
	@Inject @CreateMatrixEventQualifier Event<CreateMatrixEvent> createMatrixEvent;
	@Inject @DeleteMatrixEventQualifier Event<DeleteMatrixEvent> deleteMatrixEvent;
	@Inject @QueryMatrixEventQualifier Event<QueryMatrixEvent> queryMatrixEvent;
	@Inject @UpdateMatrixEventQualifier Event<UpdateMatrixEvent> updateMatrixEvent;
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
		createMatrixEvent.fire(new CreateMatrixEvent());
	}
	
	public void deleteMatrixProject() {
		deleteMatrixEvent.fire(new DeleteMatrixEvent());
	}
	
	public void updateMatrixProject(){
		updateMatrixEvent.fire(new UpdateMatrixEvent());
	}
	
	public void queryMatrixProject() {
		queryMatrixEvent.fire(new QueryMatrixEvent(QueryMode.LOAD_ALL_RECORD));
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
