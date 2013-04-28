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

import cn.com.nsv.tb.api.NsvTbApi;
import cn.com.nsv.tb.api.NsvTbApi.IRequestMaker;
import cn.com.nsv.tb.api.oauth.NsvOauthApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spstudio.love.system.LoveDaemon;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.nav.SystemNav;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.SystemRemoteBean;
import com.spstudio.love.system.qualifier.SystemSingleRemoteBean;
import com.spstudio.love.system.tool.JSecurityCheckHelper;
import com.spstudio.love.system.tool.PasswordGenerator;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.Item;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;

@Model
public class SysCore {
	
	@Inject @SystemRemoteBean ISystem systemRemoteBean;
	@Inject @SystemSingleRemoteBean ISystemSingleton systemSingleton;
	@Inject UserInfo userInfo;
	@Inject NsvTbApi api;
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
		
		final String host = systemSingleton.retrieveSystemConfiguration().get("HOST").get(0);
		final String appKey = systemSingleton.retrieveSystemConfiguration().get("APPKEY").get(0);
		final String secretKey = systemSingleton.retrieveSystemConfiguration().get("APPSECRET").get(0);
		final String contextPath = context.getExternalContext().getRequestContextPath(); // '/love-web'
		final String servletPath = context.getExternalContext().getRequestServletPath(); // '/pages/common/rlogin.faces'
		final Map<String, String> returnMap = context.getExternalContext().getRequestParameterMap();
		
		String sid = ((HttpSession)context.getExternalContext().getSession(true)).getId();
		
		if (returnMap.containsKey("code")){
			String returnJson = new NsvOauthApi().login(new NsvOauthApi.Setting() {
				
				@Override
				public String getAppKey() {
					return appKey;
				}
				@Override
				public String getAppSecret() {
					return secretKey;
				}
				@Override
				public String getRedirectUrl() {
					return host + contextPath + servletPath;
				}
				@Override
				public String getCode() {
					return returnMap.get("code");
				}
			});
			System.out.println(returnJson);
			Gson gson = new Gson();
			Map<String, Object> map = gson.fromJson(returnJson, new TypeToken<Map<String, Object>>(){}.getType());
			String sessionKey = (String)map.get("access_token");
			userInfo.setSessionKey(sessionKey);
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
			helper.doCheck("streetpoet", "213231", sid, host + contextPath + "/pages/home/home.faces", host + contextPath + "/pages/home/j_security_check");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// 获取当前会话用户出售中的商品列表
		long start = System.currentTimeMillis();
		ItemsOnsaleGetResponse taobaoResponse = (ItemsOnsaleGetResponse) api.execute(new IRequestMaker() {
			
			@Override
			public TaobaoRequest<? extends TaobaoResponse> buildRequest() {
				ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
				req.setFields(" approve_status,num_iid,title,nick,type,cid,pic_url,num,props,,price,delist_time,postage_id,seller_cids");
				req.setPageNo(1L);
				req.setOrderBy("modified:desc");
				req.setPageSize(40L);
				return req;
			}
		});
		
		for (Item item: taobaoResponse.getItems()){
			
			final long iid = item.getNumIid();
			
			/*
			 * open thread
			 */
			new Thread(){
				public void run(){
					
					System.out.println("iid = " + iid);
					ItemGetResponse rsp = (ItemGetResponse)api.execute(new IRequestMaker() {

						@Override
						public TaobaoRequest<? extends TaobaoResponse> buildRequest() {
							ItemGetRequest req = new ItemGetRequest();
							req.setFields("num_iid,title,price");
							req.setNumIid(iid);
							return req;
						}
						
					});
					System.out.println(rsp.getItem().getTitle());
				}
			}.start();
			
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println("time cost: " + (end - start));
		
		context.responseComplete();	
	}
	
}
