package com.spstudio.love.system.bean;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class UserInfo implements Serializable, Cloneable{
	
	private static final long serialVersionUID = -5386449236793012236L;
	
	private int userId;
	private int familyId;
	private String loginId;
	private String nickName;
	private String familyName;
	private String description;
	private String password;
	private String passwordRetry;
	
	public UserInfo clone(){
		UserInfo ui = null;
		try{
			ui = (UserInfo)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return ui;	
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRetry() {
		return passwordRetry;
	}

	public void setPasswordRetry(String passwordRetry) {
		this.passwordRetry = passwordRetry;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
