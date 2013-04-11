package com.spstudio.love.matrix.event;

import java.io.Serializable;

public class MatrixCreateEvent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -250112643600772L;
	private MatrixCreateMode mode;
	
	public static enum MatrixCreateMode{
		SOLUTION, PROJECT, MODULE, FUNCTION,
	}
	
	public MatrixCreateEvent(MatrixCreateMode mode){
		this.mode = mode;
	}

	public MatrixCreateMode getMode() {
		return mode;
	}

	public void setMode(MatrixCreateMode mode) {
		this.mode = mode;
	}
}
