package com.spstudio.love.matrix.event;

import interfaces.matrix.IMatrix;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.action.MatrixAction;
import com.spstudio.love.matrix.bean.MatrixProjectQueryConversation;
import com.spstudio.love.matrix.entity.MatrixFunction;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.helper.MatrixProjectCondition;
import com.spstudio.love.matrix.qualifier.MatrixModuleQualifier;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.bean.PageObject;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.interfaces.IQueryResult;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class MatrixQueryEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -64089072085849L;
	
	@Inject MatrixProjectQueryConversation matrixProjectQueryConversation;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject MatrixAction matrixAction;
	@Inject @MatrixRemoteBean IMatrix matrixRemoteBean;
	@Inject @LoveLogged Logger log;
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;
	@Inject @MatrixModuleQualifier MatrixModule matrixModule;
	@Inject MatrixFunction matrixFunction;

	public void queryMatrixProject(@Observes @MatrixQueryEventQualifier MatrixQueryEvent event){
		switch (event.getQueryMode()) {
		case LOAD_ALL_RECORD:
			loadAllMatrix();
			break;
		case LOAD_SINGLE_MODULE:
			loadSingleMatrixModule();
			break;
		case LOAD_SINGLE_FUNCTION:
			loadSingleMatrixFunction();
			break;
		case LOAD_SINGLE_PROJECT:
			loadSingleMatrixProject();
			break;
		default:
			break;
		}
	}
	
	private void loadSingleMatrixProject(){
		MatrixProject project = matrixRemoteBean.loadMatrixProject(matrixAction.getSelectedProjectId());
		if (project != null){
			matrixProject.setMatrixProject(project);
		}
	}
	
	private void loadSingleMatrixFunction(){
		MatrixFunction function = matrixRemoteBean.loadMatrixFunction(matrixAction.getSelectedFunctionId());
		if (function != null){
			matrixFunction.setMatrixProject(function);
		}
	}
	
	private void loadSingleMatrixModule(){
		MatrixModule module = matrixRemoteBean.loadMatrixModule(matrixAction.getSelectedModuleId());
		if (module != null){
			matrixModule.setMatrixModule(module);
		}
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
