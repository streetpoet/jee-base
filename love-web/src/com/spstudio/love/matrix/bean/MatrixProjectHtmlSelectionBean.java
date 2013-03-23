package com.spstudio.love.matrix.bean;

import interfaces.matrix.IMatrixSingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.matrix.entity.MatrixProject;
import com.spstudio.love.matrix.qualifier.MatrixSingleRemoteBean;

@Dependent
public class MatrixProjectHtmlSelectionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1262970165988845880L;
	
	@Inject @MatrixSingleRemoteBean IMatrixSingleton matrixSingleton;
	private List<SelectItem> matrixProjectList;

	public List<SelectItem> getMatrixProjectList() {
		if (matrixProjectList != null){
			return matrixProjectList;
		}
		List<MatrixProject> list = matrixSingleton.retrieveProjectList();
		matrixProjectList = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (MatrixProject project: list){
				matrixProjectList.add(new SelectItem(project.getId(), project.getProjectName()));
			}
		}
		return matrixProjectList;
	}
}
