package com.spstudio.love.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.product.qualifier.Product
public class Product implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1555058712810093401L;
	
	private int id;
	private int classifyId;
	private String classifyName;
	private int familyId;
	private int forUserId;
	private String forUserName;
	private int price;
	private String productName;
	private Date buyDate;
	private Date warrantyEndDate;
	private String description;
	
	public void clear(){
		id = 0;
		classifyId = -1;
		classifyName = "";
		familyId = -1;
		forUserId = -1;
		forUserName = "";
		price = 0;
		productName = "";
		buyDate = null;
		warrantyEndDate = null;
		description = "";
	}
	
	public void setProduct(Product p){
		id = p.id;
		classifyId = p.classifyId;
		classifyName = p.classifyName;
		familyId = p.familyId;
		forUserId = p.forUserId;
		forUserName = p.forUserName;
		price = p.price;
		productName = p.productName;
		buyDate = p.buyDate;
		warrantyEndDate = p.warrantyEndDate;
		description = p.description;
	}
	
	public Product clone(){
		Product p = null;
		try{
			p = (Product)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return p;	
	}
	
	public int getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public int getForUserId() {
		return forUserId;
	}
	public void setForUserId(int forUserId) {
		this.forUserId = forUserId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getWarrantyEndDate() {
		return warrantyEndDate;
	}
	public void setWarrantyEndDate(Date warrantyEndDate) {
		this.warrantyEndDate = warrantyEndDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getForUserName() {
		return forUserName;
	}

	public void setForUserName(String forUserName) {
		this.forUserName = forUserName;
	}
	
}
