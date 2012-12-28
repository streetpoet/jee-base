package com.spstudio.love.web.login;

import interfaces.IProducts;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.spstudio.love.web.common.StandardNavigation;
import com.spstudio.love.web.qualifiers.LoveLogged;

@javax.enterprise.context.RequestScoped
@Named
public class Executor {
	
	@Resource(mappedName = "java:global/love-ejb/ProductsBean")
//	@EJB(mappedName="ProductsBean/remote")
	private IProducts productsBean;
	
	public Object doExecute(){
		try{
			productsBean.queryProducts(-1, -1);
//			InitialContext ic = new InitialContext();
//			IProducts example = (IProducts)ic.lookup("ProductsBean/remote");
//			example.queryProducts(-1, -1);
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
