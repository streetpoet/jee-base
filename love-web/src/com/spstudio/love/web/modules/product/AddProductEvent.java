package com.spstudio.love.web.modules.product;

import java.io.Serializable;

public class AddProductEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -530870361147034377L;

	private int classifyId;

	public int getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}
}
