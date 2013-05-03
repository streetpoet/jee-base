package ${project.packageString}.${module.moduleName}.event;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.${module.moduleName}.qualifier.${module.entityBeanName?cap_first}Qualifier;
import ${project.packageString}.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import ${project.packageString}.system.bean.UserInfo;
import ${project.packageString}.system.qualifier.${project.projectCode?cap_first}Trace;
import ${project.packageString}.system.qualifier.UserInfoQualifier;

@Dependent
public class ${module.moduleName?cap_first}CreateEventHandler implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject @${module.entityBeanName?cap_first}Qualifier ${module.entityBeanName?cap_first} ${module.entityBeanName};
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@${project.projectCode?cap_first}Trace
	public void create${module.entityBeanName?cap_first}(@Observes @${module.moduleName?cap_first}CreateEventQualifier ${module.moduleName?cap_first}CreateEvent event){
		
		${module.entityBeanName?cap_first} temp${module.entityBeanName?cap_first} = ${module.entityBeanName}.clone();
		boolean result = ${module.moduleName}RemoteBean.create${module.entityBeanName?cap_first}(temp${module.entityBeanName?cap_first});
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		${module.moduleName}Project.clear();
	}
}
