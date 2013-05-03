package ${project.packageString}.${module.moduleName}.event;

import interfaces.${module.moduleName}.I${module.moduleName?cap_first};

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ${project.packageString}.${module.moduleName}.entity.${module.entityBeanName?cap_first};
import ${project.packageString}.${module.moduleName}.qualifier.${module.entityBeanName?cap_first}Qualifier;
import ${project.packageString}.${module.moduleName}.qualifier.${module.moduleName?cap_first}RemoteBean;
import ${project.packageString}.system.bean.UserInfo;
import ${project.packageString}.system.qualifier.LoveTrace;
import ${project.packageString}.system.qualifier.UserInfoQualifier;

@Dependent
public class ${module.moduleName?cap_first}UpdateEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;
	
	@Inject @${module.entityBeanName?cap_first}Qualifier ${module.entityBeanName?cap_first} ${module.entityBeanName};
	@Inject @${module.moduleName?cap_first}RemoteBean I${module.moduleName?cap_first} ${module.moduleName}RemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void update${module.entityBeanName?cap_first}(@Observes @${module.moduleName?cap_first}UpdateEventQualifier ${module.moduleName?cap_first}UpdateEvent event){
		
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
