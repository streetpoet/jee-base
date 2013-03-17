package com.spstudio.love.${module.moduleName}.event;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.io.Serializable;

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
public class Create${module.moduleName?cap_first}Handler implements Serializable {
	
	@Inject @${module.entityBeanName?cap_first}Qualifier ${module.entityBeanName?cap_first} ${module.moduleName}Project;
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void create${module.entityBeanName?cap_first}(@Observes @Create${module.moduleName?cap_first}EventQualifier Create${module.moduleName?cap_first}Event event){
		
		${module.entityBeanName?cap_first} temp${module.entityBeanName?cap_first} = ${module.moduleName}Project.clone();
		boolean result = ${module.moduleName}RemoteBean.create${module.entityBeanName?cap_first}(temp${module.entityBeanName?cap_first});
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		${module.moduleName}Project.clear();
	}
}
