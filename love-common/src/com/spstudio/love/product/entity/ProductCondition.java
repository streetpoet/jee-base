package com.spstudio.love.product.entity;

import java.util.Date;

import javax.enterprise.inject.Model;

@Model
@com.spstudio.love.product.qualifier.ProductCondition
public class ProductCondition extends Product implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -832712246031293176L;
	
	private Date buyDateFrom;
	private Date buyDateTo;
	private Date warrantyEndDateFrom;
	private Date warrantyEndDateTo;
	
	public ProductCondition clone(){
		return (ProductCondition)super.clone();
	}
	
	public Date getBuyDateFrom() {
		return buyDateFrom;
	}
	public void setBuyDateFrom(Date buyDateFrom) {
		this.buyDateFrom = buyDateFrom;
	}
	public Date getBuyDateTo() {
		return buyDateTo;
	}
	public void setBuyDateTo(Date buyDateTo) {
		this.buyDateTo = buyDateTo;
	}
	public Date getWarrantyEndDateFrom() {
		return warrantyEndDateFrom;
	}
	public void setWarrantyEndDateFrom(Date warrantyEndDateFrom) {
		this.warrantyEndDateFrom = warrantyEndDateFrom;
	}
	public Date getWarrantyEndDateTo() {
		return warrantyEndDateTo;
	}
	public void setWarrantyEndDateTo(Date warrantyEndDateTo) {
		this.warrantyEndDateTo = warrantyEndDateTo;
	}
	
}
