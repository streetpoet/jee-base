package ${project.packageString}.system.action;

import interfaces.system.ISystem;
import interfaces.system.ISystemSingleton;

import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ${project.packageString}.system.LoveDaemon;
import ${project.packageString}.system.bean.UserInfo;
import ${project.packageString}.system.nav.SystemNav;
import ${project.packageString}.system.qualifier.LoveTrace;
import ${project.packageString}.system.qualifier.SystemRemoteBean;
import ${project.packageString}.system.qualifier.SystemSingleRemoteBean;
import ${project.packageString}.system.tool.JSecurityCheckHelper;
import ${project.packageString}.system.tool.PasswordGenerator;

@Model
public class SysCore {
	
	@Inject @SystemRemoteBean ISystem systemRemoteBean;
	@Inject @SystemSingleRemoteBean ISystemSingleton systemSingleton;
	@Inject UserInfo userInfo;
	@EJB LoveDaemon loveDaemon;
	
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
	
	@LoveTrace
	public void remoteLogin(){
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			JSecurityCheckHelper helper = new JSecurityCheckHelper();
			helper.doCheck("streetpoet", "213231", "sessionId", "protected url", "j_security_check url");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		context.responseComplete();	
	}
	
}
