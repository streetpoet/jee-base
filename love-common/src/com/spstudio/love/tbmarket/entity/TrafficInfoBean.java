package com.spstudio.love.tbmarket.entity;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
public class TrafficInfoBean implements Cloneable, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6383483963475158202L;
	
	private int id;
	private String email;
	private String fromPlace;
	private String toPlace;
	
	public void clear(){
		id = 0;
	}
	
	public void setTbAcountBean(TrafficInfoBean trafficInfoBean){
		id = trafficInfoBean.id;
	}
	
	public TrafficInfoBean clone(){
		TrafficInfoBean tbAcountBean = null;
		try{
			tbAcountBean = (TrafficInfoBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return tbAcountBean;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFromPlace() {
		return fromPlace;
	}

	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	public String getToPlace() {
		return toPlace;
	}

	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	
}
