package com.spstudio.love.web.modules.product;

import interfaces.IProduct;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.web.entity.product.Product;
import com.spstudio.love.web.helper.DatabaseHelper;
import com.spstudio.love.web.modules.main.UserInfo;
import com.spstudio.love.web.qualifiers.LoveLogged;
import com.spstudio.love.web.qualifiers.LoveTrace;
import com.spstudio.love.web.qualifiers.ProductRemoteBean;

@Dependent
public class AddProductHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject @LoveLogged Logger log;
	@Inject Product product;
	@Inject UserInfo userInfo;
	@Inject @ProductRemoteBean IProduct productRemoteBean;

	@LoveTrace
	public void addProduct(@Observes @AddProductQualifier AddProductEvent event){
		
		product.setFamilyId(userInfo.getFamilyId());
		Product p = product.clone();
		boolean result = productRemoteBean.addProduct(p);
		System.out.println("result = " + result);
		
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("执行结果", "成功"));
		product.clear();
	}
}
