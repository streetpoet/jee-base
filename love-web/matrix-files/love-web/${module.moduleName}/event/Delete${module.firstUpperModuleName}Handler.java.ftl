package com.spstudio.love.${module.moduleName}.event;

import interfaces.I${module.firstUpperModuleName};

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.${module.moduleName}.entity.${module.firstUpperModuleName};
import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}Qualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.firstUpperModuleName}RemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class Delete${module.firstUpperModuleName}Handler implements Serializable {
	
	@Inject @${module.firstUpperModuleName}Qualifier ${module.firstUpperModuleName} ${module.moduleName};
	@Inject @${module.firstUpperModuleName}RemoteBean I${module.firstUpperModuleName} ${module.moduleName}RemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void delete${module.firstUpperModuleName}(@Observes @Delete${module.firstUpperModuleName}EventQualifier Delete${module.firstUpperModuleName}Event event){
		
		int result = ${module.moduleName}RemoteBean.delete${module.firstUpperModuleName}(${module.moduleName}.clone());
		FacesContext context = FacesContext.getCurrentInstance();
		
		ResourceBundle bundle = ResourceBundle.getBundle("messages.${module.moduleName}.Message", context.getViewRoot().getLocale());
		switch (result) {
		case 1:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("${module.moduleName}.modify.msg.success"), ""));
			break;

		default:
			context.addMessage(FacesMessage.FACES_MESSAGES, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("${module.moduleName}.modify.msg.failure"), ""));
			break;
		}
	}
}
