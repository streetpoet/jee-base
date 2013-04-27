package cn.com.nsv.tb.api.oauth;

import java.util.HashMap;
import java.util.Map;

import com.taobao.api.internal.util.WebUtils;

public class NsvOauthManager {
	
	public String login(Setting setting){
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "authorization_code");
		param.put("code", setting.getCode());
		param.put("client_id", setting.getAppKey());
		param.put("client_secret", setting.getAppSecret());
		param.put("redirect_uri", setting.getRedirectUrl());
		param.put("scope", "item");
		param.put("view", "web");
		try{
			String responseJson = WebUtils.doPost("https://oauth.taobao.com/token", param, 5000, 5000);
			return responseJson;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static abstract class Setting{
		public abstract String getCode();
		public abstract String getAppKey();
		public abstract String getAppSecret();
		public abstract String getRedirectUrl();
	}
	
	public static void main(String args[]){
		System.out.println(new NsvOauthManager().login(new Setting() {
			
			@Override
			public String getRedirectUrl() {
				return "http://127.0.0.1:8080/love-web/pages/common/rlogin.faces";
			}
			
			@Override
			public String getCode() {
				return "KLFXdz37LpMs66QPJCD9fUr2850489";
			}
			
			@Override
			public String getAppSecret() {
				return "9fbd0917c21560a44e3fe133b086af3a";
			}
			
			@Override
			public String getAppKey() {
				return "21478739";
			}
		}));
	}
}
