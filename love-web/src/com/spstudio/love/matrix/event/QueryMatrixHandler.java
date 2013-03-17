package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.action.MatrixAction;
import com.spstudio.love.matrix.bean.MatrixProjectQueryConversation;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.helper.MatrixProjectCondition;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class QueryMatrixHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -122411910109058L;
	
	@Inject MatrixProjectQueryConversation matrixProjectQueryConversation;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject MatrixAction matrixAction;
	@Inject @MatrixRemoteBean IMatrix matrixRemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;

	public void queryMatrixProject(@Observes @QueryMatrixEventQualifier QueryMatrixEvent event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_RECORD:
			loadAllMatrix();
			break;
		case LOAD_SINGLE_RECORD:
			loadSingleMatrix();
			break;

		default:
			break;
		}
	}
	
	private void loadSingleMatrix(){
		MatrixProject matrixProject = matrixRemoteBean.loadMatrixProject(this.matrixProject.getId());
		this.matrixProject.setMatrixProject(matrixProject);
	}
	
	private void loadAllMatrix(){
		MatrixProjectCondition matrixProjectCondition = matrixProjectQueryConversation.getMatrixProjectCondition();
		MatrixProjectCondition tempMatrixProjectCondition = matrixProjectCondition.clone();
		PageObject pageObject = matrixProjectQueryConversation.getPageObject();
		IQueryResult<MatrixProject> result = matrixRemoteBean.queryMatrixProject(tempMatrixProjectCondition, pageObject.clone());
		matrixProjectQueryConversation.setListMatrixProject(result.getResultData());
		
		// set paging object
		pageObject.setTotalRecordsNumber(result.getPageObject().getTotalRecordsNumber());
		pageObject.setCurrentPageNumber(pageObject.getOffset() / (int)pageObject.getMaxRecordsPerPage() + 1);
		pageObject.setMaxPageNumber((int)pageObject.getTotalRecordsNumber() / pageObject.getMaxRecordsPerPage() + ((int)pageObject.getTotalRecordsNumber() % pageObject.getMaxRecordsPerPage() == 0 ? 0 : 1));

	}
}
