package com.spstudio.love.product.helper;

import java.util.Date;

import javax.enterprise.inject.Model;

import com.spstudio.love.product.entity.Product;

@com.spstudio.love.product.qualifier.ProductCondition
@Model
public class ProductCondition extends Product implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -832712246031293176L;
	
	private Date buyDateFrom;
	private Date buyDateTo;
	private Date warrantyEndDateFrom;
	private Date warrantyEndDateTo;
	private int priceFrom;
	private int priceTo;
	
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
	public int getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(int priceFrom) {
		this.priceFrom = priceFrom;
	}
	public int getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(int priceTo) {
		this.priceTo = priceTo;
	}
	
}
