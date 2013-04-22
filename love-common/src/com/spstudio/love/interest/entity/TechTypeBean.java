package com.spstudio.love.interest.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
public class TechTypeBean implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -69282028277700L;
	
	private int id = -1;
	private String label;
	private int classifyFirstId;
	private String classifyFirstLabel;
	private int classifySecondId;
	private String classifySecondLabel;
	private int classifyFirstRowSpan;
	
	public void clear(){
		id = -1;
		label = "";
	}
	
	public void setTechTypeBean(TechTypeBean TechTypeBean){
		id = TechTypeBean.id;
		label = TechTypeBean.label;
	}
	
	public TechTypeBean clone(){
		TechTypeBean TechTypeBean = null;
		try{
			TechTypeBean = (TechTypeBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return TechTypeBean;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getClassifyFirstId() {
		return classifyFirstId;
	}

	public void setClassifyFirstId(int classifyFirstId) {
		this.classifyFirstId = classifyFirstId;
	}

	public String getClassifyFirstLabel() {
		return classifyFirstLabel;
	}

	public void setClassifyFirstLabel(String classifyFirstLabel) {
		this.classifyFirstLabel = classifyFirstLabel;
	}

	public int getClassifySecondId() {
		return classifySecondId;
	}

	public void setClassifySecondId(int classifySecondId) {
		this.classifySecondId = classifySecondId;
	}

	public String getClassifySecondLabel() {
		return classifySecondLabel;
	}

	public void setClassifySecondLabel(String classifySecondLabel) {
		this.classifySecondLabel = classifySecondLabel;
	}

	public int getClassifyFirstRowSpan() {
		return classifyFirstRowSpan;
	}

	public void setClassifyFirstRowSpan(int classifyFirstRowSpan) {
		this.classifyFirstRowSpan = classifyFirstRowSpan;
	}
}
