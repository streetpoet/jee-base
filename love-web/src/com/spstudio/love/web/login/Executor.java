package com.spstudio.love.web.login;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.DatabaseHelper;
import com.spstudio.love.web.common.StandardNavigation;

@RequestScoped
@ManagedBean
public class Executor {
	
	private Logger logger = Logger.getLogger(Executor.class);
	
	public Object doExecute(){
		logger.trace("### doExecute");
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return StandardNavigation.SUCCESS;
	}
	
	public void logout(){
		
		LoginContext lc = null;
		try{
			lc = new LoginContext("love-web", new CallbackHandler() {
				
				@Override
				public void handle(Callback[] callBack) throws IOException,
						UnsupportedCallbackException {
					for (Callback c: callBack){
						if (c instanceof NameCallback){
							NameCallback nameCallback = (NameCallback)c;
							nameCallback.setName("streetpoet");
						}else if (c instanceof PasswordCallback){
							PasswordCallback passwordCallback = (PasswordCallback)c;
							passwordCallback.setPassword("213231".toCharArray());
						}else {
							throw new UnsupportedCallbackException(c, "Unrecognized Callback");
						}
					}
				}
			});
			lc.login();
			Subject loginSubject = lc.getSubject();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
