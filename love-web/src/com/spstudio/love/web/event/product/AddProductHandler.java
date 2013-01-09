package com.spstudio.love.web.event.product;

import interfaces.IProduct;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.web.entity.product.Product;
import com.spstudio.love.web.modules.main.UserInfo;
import com.spstudio.love.web.qualifiers.product.ProductRemoteBean;
import com.spstudio.love.web.qualifiers.system.LoveTrace;

@Dependent
public class AddProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject Product product;
	@Inject UserInfo userInfo;
	@Inject @ProductRemoteBean IProduct productRemoteBean;

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
