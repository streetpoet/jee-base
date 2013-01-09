package com.spstudio.love.web.login;

import interfaces.IProductSingleton;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
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
		FacesContext.getCurrentInstance().responseComplete();
		return StandardNavigation.SUCCESS;
	}
	
	@LoveTrace
	public void logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getViewRoot().addPhaseListener(new PhaseListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7854016344535037345L;

			@Override
			public PhaseId getPhaseId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void beforePhase(PhaseEvent event) {
				
			}
			
			@Override
			public void afterPhase(PhaseEvent arg0) {
				
			}
		});
	}

}
