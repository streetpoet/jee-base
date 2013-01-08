package com.spstudio.love.web.modules.main;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.spstudio.love.web.qualifiers.system.UserInfoQualifier;

@SessionScoped
@Named
public class UserInfo implements Serializable{
	
	private static final long serialVersionUID = -5386449236793012236L;
	
	private int userId;
	private int familyId;
	private String nickName;
	private String familyName;
	private String description;
	
	public UserInfo(){}
	
	@Inject
	public UserInfo(@UserInfoQualifier UserInfo uInfo){
		userId = uInfo.getUserId();
		familyId = uInfo.getFamilyId();
		nickName = uInfo.getNickName();
		familyName = uInfo.getFamilyName();
		description = uInfo.getDescription();
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
	
}
