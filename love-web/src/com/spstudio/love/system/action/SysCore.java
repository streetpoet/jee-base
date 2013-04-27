package com.spstudio.love.system.action;

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

import cn.com.nsv.tb.api.oauth.NsvOauthManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spstudio.love.system.LoveDaemon;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.constant.Configuration;
import com.spstudio.love.system.nav.SystemNav;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.SystemRemoteBean;
import com.spstudio.love.system.qualifier.SystemSingleRemoteBean;
import com.spstudio.love.system.tool.JSecurityCheckHelper;
import com.spstudio.love.system.tool.PasswordGenerator;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

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
		
		final String contextPath = context.getExternalContext().getRequestContextPath(); // '/love-web'
		final String servletPath = context.getExternalContext().getRequestServletPath(); // '/pages/common/rlogin.faces'
		final Map<String, String> returnMap = context.getExternalContext().getRequestParameterMap(); 
		
		String sid = ((HttpSession)context.getExternalContext().getSession(true)).getId();
		
		if (returnMap.containsKey("code")){
			String returnJson = new NsvOauthManager().login(new NsvOauthManager.Setting() {
				
				@Override
				public String getAppKey() {
					return Configuration.APPKEY;
				}
				@Override
				public String getAppSecret() {
					return Configuration.APPSECRET;
				}
				@Override
				public String getRedirectUrl() {
					return Configuration.HOST + contextPath + servletPath;
				}
				@Override
				public String getCode() {
					System.out.println(returnMap.get("code"));
					return returnMap.get("code");
				}
			});
			System.out.println(returnJson);
			Gson gson = new Gson();
			Map<String, String> map = gson.fromJson(returnJson, new TypeToken<Map<String, String>>(){}.getType());
			String sessionKey = map.get("access_token");
			System.out.println(sessionKey);
			
			TaobaoClient client=new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", Configuration.APPKEY, Configuration.APPSECRET);
			UserSellerGetRequest req=new UserSellerGetRequest();
			req.setFields("nick,sex");
			try{
				UserSellerGetResponse response = client.execute(req, sessionKey);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if (returnMap.containsKey("error")){
			
		}
		
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		String url = context.getExternalContext().getRequestContextPath(); // '/love-web'
		try{
			response.sendRedirect(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			JSecurityCheckHelper helper = new JSecurityCheckHelper();
			helper.doCheck("streetpoet", "213231", sid, Configuration.HOST + contextPath + "/pages/home/home.faces", Configuration.HOST + contextPath + "/pages/home/j_security_check");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		context.responseComplete();	
	}
}
