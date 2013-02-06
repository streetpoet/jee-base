package com.spstudio.love.plan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.inject.Model;

import com.spstudio.love.plan.qualifier.PlanQualifier;

@Model
@PlanQualifier
public class Plan implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5064923946927842263L;
	
	private int id;
	private int familyId;
	private int forUserId;
	private int typeId;
	private String title;
	private String content;
	private boolean needMoney;
	private int amount;
	private Date terminalDate;
	private Date entryDatetime;
	private int entryUserId;
	private boolean isRepeat;
	private int repeatUnit;
	
	
	
	public void clear(){
		id = 0;
		title = "";
		content = "";
		amount = 0;
		terminalDate = null;
		repeatUnit = 1;
	}
	
	public void setProduct(Plan p){
		id = p.id;
		familyId = p.familyId;
		forUserId = p.forUserId;
		typeId = p.typeId;
		title = p.title;
		content = p.content;
		needMoney = p.needMoney;
		amount = p.amount;
		terminalDate = p.terminalDate;
		entryDatetime = p.entryDatetime;
		entryUserId = p.entryUserId;
		isRepeat = p.isRepeat;
		repeatUnit = p.repeatUnit;
	}
	
	public Plan clone(){
		Plan p = null;
		try{
			p = (Plan)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return p;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public int getForUserId() {
		return forUserId;
	}

	public void setForUserId(int forUserId) {
		this.forUserId = forUserId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isNeedMoney() {
		return needMoney;
	}

	public void setNeedMoney(boolean needMoney) {
		this.needMoney = needMoney;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getTerminalDate() {
		return terminalDate;
	}

	public void setTerminalDate(Date terminalDate) {
		this.terminalDate = terminalDate;
	}

	public Date getEntryDatetime() {
		return entryDatetime;
	}

	public void setEntryDatetime(Date entryDatetime) {
		this.entryDatetime = entryDatetime;
	}

	public int getEntryUserId() {
		return entryUserId;
	}

	public void setEntryUserId(int entryUserId) {
		this.entryUserId = entryUserId;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public int getRepeatUnit() {
		return repeatUnit;
	}

	public void setRepeatUnit(int repeatUnit) {
		this.repeatUnit = repeatUnit;
	}
	
}
