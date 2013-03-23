package com.spstudio.love.matrix.bean;

import interfaces.matrix.IMatrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.matrix.entity.MatrixModule;
import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.qualifier.MatrixProjectQualifier;
import com.spstudio.love.matrix.qualifier.MatrixRemoteBean;
import com.spstudio.love.system.qualifier.LoveLogged;

@Dependent
public class MatrixModuleHtmlSelectionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4891784392769095369L;
	
	@Inject @MatrixRemoteBean IMatrix matrixBean;
	@Inject @LoveLogged Logger log;
	@Inject @MatrixProjectQualifier MatrixProject matrixProject;
	private List<SelectItem> matrixModuleList;

	public List<SelectItem> getMatrixModuleList() {
		if (matrixModuleList != null){
			return matrixModuleList;
		}
		matrixModuleList = new ArrayList<SelectItem>();
		if (matrixProject.getId() != -1){
			List<MatrixModule> list = matrixBean.loadMatrixModuleList(matrixProject.getId());
			if (list != null && list.size() != 0){
				for (MatrixModule module: list){
					matrixModuleList.add(new SelectItem(module.getId(), module.getModuleName()));
				}
			}
		}
		return matrixModuleList;
	}

	public void reloadModleListByProjectId(int projectId) {
		matrixModuleList = new ArrayList<SelectItem>();
		List<MatrixModule> list = matrixBean.loadMatrixModuleList(projectId);
		if (list != null && list.size() != 0){
			for (MatrixModule module: list){
				matrixModuleList.add(new SelectItem(module.getId(), module.getModuleName()));
			}
		}
	}
}
