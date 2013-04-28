package cn.com.nsv.tb.api;

import interfaces.system.ISystemSingleton;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveLogged;
import com.spstudio.love.system.qualifier.SystemSingleRemoteBean;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;

@SessionScoped
public class NsvTbApi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8289090949307684710L;
	
	@Inject @SystemSingleRemoteBean ISystemSingleton systemSingleton;
	@Inject UserInfo user;
	@Inject @LoveLogged Logger log;
	
	private String url;
	private String appkey;
	private String secret;
	private String sessionKey;
	private TaobaoClient client;
	
	@PostConstruct
	public void construct(){
		url = systemSingleton.retrieveSystemConfiguration().get("APIURL").get(0);
		appkey = systemSingleton.retrieveSystemConfiguration().get("APPKEY").get(0);
		secret = systemSingleton.retrieveSystemConfiguration().get("APPSECRET").get(0);
		sessionKey = user.getSessionKey();
		client = new DefaultTaobaoClient(url, appkey, secret);
	}
	
	public TaobaoResponse execute(IRequestMaker requestMaker){
		TaobaoResponse response = null;
		try{
			response = client.execute(requestMaker.buildRequest(), sessionKey);
		}catch(Exception e){
			log.error("[userId = " + user.getUserId() + ", nickName = " + user.getNickName() + "] " + e.getMessage());
			e.printStackTrace();
		}
		return response; 
	}
	
	public static interface IRequestMaker{
		public TaobaoRequest<? extends TaobaoResponse> buildRequest();
	}
}
