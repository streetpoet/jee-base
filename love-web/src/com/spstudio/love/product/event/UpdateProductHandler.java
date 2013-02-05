package com.spstudio.love.product.event;

import interfaces.IProduct;

import java.io.Serializable;
import java.util.ResourceBundle;

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
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class UpdateProductHandler implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9175620829519131096L;
	
	@Inject @ProductQualifier Product product;
	@Inject @ProductRemoteBean IProduct productRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void updateProduct(@Observes @UpdateProductEventQualifier UpdateProductEvent event){
		
		int result = productRemoteBean.updateProduct(product.clone());
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages.product.Message", context.getViewRoot().getLocale());
		switch (result) {
		case 1:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("product.modify.msg.success"), ""));
			break;

		default:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("product.modify.msg.failure"), ""));
			break;
		}
	}
}
