package com.spstudio.love.matrix.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
public class MatrixFunction implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7271837435147191440L;
	private int id = -1;
	private String functionName;
	
	public void clear(){
		id = -1;
	}
	
	public void setMatrixProject(MatrixFunction matrixFunction){
		id = matrixFunction.id;
		functionName = matrixFunction.functionName;
	}
	
	public MatrixFunction clone(){
		MatrixFunction matrixFunction = null;
		try{
			matrixFunction = (MatrixFunction)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return matrixFunction;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
}
