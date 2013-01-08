package com.spstudio.love.web.login;

import interfaces.IProductSingleton;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.spstudio.love.web.qualifiers.product.ProductSingleRemoteBean;
import com.spstudio.love.web.qualifiers.system.LoveTrace;
import com.spstudio.love.web.system.StandardNavigation;

@RequestScoped
@Named
public class Executor {
		
	@Inject
	@ProductSingleRemoteBean
	private IProductSingleton productSingleton;
	
	public Object doExecute(){
		try{
			System.out.println(productSingleton.retrieveProductClassify());
		}catch(Exception e){
			e.printStackTrace();
		}
		return StandardNavigation.SUCCESS;
	}
	
	@LoveTrace
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

}
