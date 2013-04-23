package com.spstudio.love.system.action;

import interfaces.system.ISystem;

import java.util.ResourceBundle;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.nav.SystemNav;
import com.spstudio.love.system.qualifier.SystemRemoteBean;
import com.spstudio.love.system.tool.PasswordGenerator;

@Model
public class SysCore {
	
	@Inject @SystemRemoteBean ISystem systemRemoteBean;
	@Inject UserInfo userInfo;
	
	public Object logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return SystemNav.HOME;
	}
	
	public void register(){
		if (!userInfo.getPassword().equals(userInfo.getPasswordRetry())){
			FacesContext context = FacesContext.getCurrentInstance();
			ResourceBundle bundle = ResourceBundle.getBundle("messages.interest.Message", context.getViewRoot().getLocale());
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("interest.validator.passwordnotsame"), ""));
			return;
		}
		UserInfo user = userInfo.clone();
		user.setPassword(PasswordGenerator.encryptPassword(user.getPassword()));
		systemRemoteBean.createUser(user);
	}
}
