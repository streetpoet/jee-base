package com.spstudio.love.web.login;

import interfaces.IProducts;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.spstudio.love.web.common.StandardNavigation;
import com.spstudio.love.web.qualifiers.LoveLogged;
import com.spstudio.love.web.qualifiers.ProductsEJB;

@RequestScoped
@Named
@ManagedBean
public class Executor {
		
	@Inject
	@ProductsEJB
	private IProducts products;
	
	public Object doExecute(){
		try{
			products.queryProducts(-1, -1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return StandardNavigation.SUCCESS;
	}
	
	@LoveLogged
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	
	
}
