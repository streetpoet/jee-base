package com.spstudio.love.${module.moduleName}.event;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import com.spstudio.love.${module.moduleName}.qualifier.${module.entityBeanName?cap_first}Qualifier;
import com.spstudio.love.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class Update${module.moduleName?cap_first}Handler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject @${module.entityBeanName?cap_first}Qualifier ${module.entityBeanName?cap_first} ${module.entityBeanName};
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void update${module.entityBeanName?cap_first}(@Observes @Update${module.moduleName?cap_first}EventQualifier Update${module.moduleName?cap_first}Event event){
		
		int result = ${module.moduleName}RemoteBean.update${module.entityBeanName?cap_first}(${module.entityBeanName}.clone());
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
