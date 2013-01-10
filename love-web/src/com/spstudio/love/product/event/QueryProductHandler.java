package com.spstudio.love.product.event;

import interfaces.IProduct;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.spstudio.love.product.entity.Product;
import com.spstudio.love.product.qualifier.ProductRemoteBean;
import com.spstudio.love.system.entity.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;

@Dependent
public class QueryProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject Product product;
	@Inject UserInfo userInfo;
	@Inject @ProductRemoteBean IProduct productRemoteBean;

	@LoveTrace
	public void queryProduct(@Observes @AddProductEventQualifier QueryProductEvent event){
		
		product.setFamilyId(userInfo.getFamilyId());
		Product p = product.clone();
//		boolean result = productRemoteBean.
//		FacesContext.getCurrentInstance().addMessage(
//				FacesMessage.FACES_MESSAGES, 
//				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
//		product.clear();
	}
}
