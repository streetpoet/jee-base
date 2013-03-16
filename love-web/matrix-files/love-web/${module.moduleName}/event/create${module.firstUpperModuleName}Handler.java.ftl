package com.spstudio.love.${module.moduleName}.event;

import interfaces.I${module.firstUpperModuleName};

import java.io.Serializable;

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
public class create${module.firstUpperModuleName}Handler implements Serializable {
	
	@Inject @${module.firstUpperModuleName}Qualifier ${module.firstUpperModuleName} ${module.moduleName};
	@Inject @${module.firstUpperModuleName}RemoteBean I${module.firstUpperModuleName} ${module.moduleName}RemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void add${module.firstUpperModuleName}(@Observes @create${module.firstUpperModuleName}EventQualifier create${module.firstUpperModuleName}Event event){
		
		${module.moduleName}.setFamilyId(userInfo.getFamilyId());
		${module.firstUpperModuleName} p = ${module.moduleName}.clone();
		boolean result = ${module.moduleName}RemoteBean.add${module.firstUpperModuleName}(p);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		${module.moduleName}.clear();
	}
}
