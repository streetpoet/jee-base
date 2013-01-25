package com.spstudio.love.product.event;

import interfaces.IProduct;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.qualifier.ProductQualifier;
import com.spstudio.love.product.qualifier.ProductRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;

@Dependent
public class AddProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject @ProductQualifier Product product;
	@Inject @ProductRemoteBean IProduct productRemoteBean;
	@Inject UserInfo userInfo;

	@LoveTrace
	public void addProduct(@Observes @AddProductEventQualifier AddProductEvent event){
		
		product.setFamilyId(userInfo.getFamilyId());
		Product p = product.clone();
		boolean result = productRemoteBean.addProduct(p);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		product.clear();
	}
}
