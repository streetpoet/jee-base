package com.spstudio.love.web.modules.main;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.spstudio.love.web.qualifiers.QueryUserInfoQualifier;

@SessionScoped
@ManagedBean
@Named
public class UserInfo {
	
	@Inject
	@QueryUserInfoQualifier
	private UserInfo userInfo;
	
	private String userId;
	private String nickName;
	private String familyName;
	private String description;
	
	public String getUserId() {
		return userInfo.userId;
	}
	public String getNickName() {
		return userInfo.nickName;
	}
	public String getFamilyName() {
		return userInfo.familyName;
	}
	public String getDescription() {
		return userInfo.description;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
