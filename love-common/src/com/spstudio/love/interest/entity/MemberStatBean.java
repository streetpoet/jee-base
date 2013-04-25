package com.spstudio.love.interest.entity;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;

@Model
public class MemberStatBean implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8756628611239056508L;
	
	private int id;
	private int userId;
	private String nickName;
	private List<String> techClassifyName;
	
	public void clear(){
		id = 0;
	}
	
	public void setTechSelectBean(MemberStatBean TechSelectBean){
		id = TechSelectBean.id;
	}
	
	public MemberStatBean clone(){
		MemberStatBean memberStatBean = null;
		try{
			memberStatBean = (MemberStatBean)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return memberStatBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<String> getTechClassifyName() {
		return techClassifyName;
	}

	public void setTechClassifyName(List<String> techClassifyName) {
		this.techClassifyName = techClassifyName;
	}
}
