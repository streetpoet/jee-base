package com.spstudio.love.matrix.bean;

import interfaces.matrix.IMatrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.entity.MatrixFunction;
import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.qualifier.MatrixModuleQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.qualifier.LoveLogged;

@Dependent
public class MatrixFunctionHtmlSelectionBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2843753939531484593L;
	
	@Inject @MatrixRemoteBean IMatrix matrixBean;
	@Inject @LoveLogged Logger log;
	@Inject @MatrixModuleQualifier MatrixModule matrixModule;
	private List<SelectItem> matrixFunctionList;

	public List<SelectItem> getMatrixFunctionList() {
		if (matrixFunctionList != null){
			return matrixFunctionList;
		}
		matrixFunctionList = new ArrayList<SelectItem>();
		if (matrixModule.getId() != -1){
			List<MatrixFunction> list = matrixBean.loadMatrixFunctionList(matrixModule.getId());
			if (list != null && list.size() != 0){
				for (MatrixFunction func: list){
					matrixFunctionList.add(new SelectItem(func.getId(), func.getFunctionName()));
				}
			}
		}
		return matrixFunctionList;
	}

	public void reloadFunctionListByModuleId(int moduleId) {
		matrixFunctionList = new ArrayList<SelectItem>();
		List<MatrixFunction> list = matrixBean.loadMatrixFunctionList(moduleId);
		if (list != null && list.size() != 0){
			for (MatrixFunction func: list){
				matrixFunctionList.add(new SelectItem(func.getId(), func.getFunctionName()));
			}
		}
	}
}
