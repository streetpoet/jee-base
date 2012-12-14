package com.spstudio.love.web.login;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.security.jacc.PolicyContext;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.DatabaseHelper;
import com.spstudio.love.web.common.StandardNavigation;
import com.spstudio.love.web.system.LoveDaemon;

@RequestScoped
@ManagedBean
public class Login {
	
	private Logger logger = Logger.getLogger(Login.class);
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Object doLogin(){
//		LoginContext lc = null;
//		try{
//			lc = new LoginContext("love-web", new CallbackHandler() {
//				
//				@Override
//				public void handle(Callback[] callBack) throws IOException,
//						UnsupportedCallbackException {
//					for (Callback c: callBack){
//						log.info("Callback name: " + c.getClass().getCanonicalName());
//						if (c instanceof NameCallback){
//							NameCallback nameCallback = (NameCallback)c;
//							nameCallback.setName(username);
//						}else if (c instanceof PasswordCallback){
//							PasswordCallback passwordCallback = (PasswordCallback)c;
//							passwordCallback.setPassword(password.toCharArray());
//						}else {
//							throw new UnsupportedCallbackException(c, "Unrecognized Callback");
//						}
//					}
//				}
//			});
//			lc.login();
//			Subject loginSubject = lc.getSubject();
//			try{
//				MBeanServer server = MBeanServerLocator.locateJBoss();
////				MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//				ObjectName jaasMgr = new ObjectName("jboss.security:service=JaasSecurityManager");
//				Object[] params = {"love-web", new SimplePrincipal("streetpoet"), "213231"};
//				String[] signature = {"java.lang.String", "java.security.Principal", "java.lang.Object"};
//				MBeanInfo info = server.getMBeanInfo(jaasMgr);
//				MBeanOperationInfo[] infos = info.getOperations();
//				for (MBeanOperationInfo o: infos){
//					System.out.println(o.getName() + "|"+o.getReturnType());
//					MBeanParameterInfo[] pinfos =  o.getSignature();
//					for (MBeanParameterInfo i: pinfos){
//						System.out.println(i.getName() + "-" + i.getType());
//					}
//				}
//				Boolean result = (Boolean)server.invoke(jaasMgr, "isValid", params, signature);
//				System.out.println(result);
//			}catch(Exception e){
//				e.printStackTrace();
//			}

//			boolean b = SecurityFactory.getAuthenticationManager("love-web").isValid(new SimplePrincipal("streetpoet"), "213231", lc.getSubject());
//			System.out.println(b);
			Object o = ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true));
			FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			try{
				Object object = PolicyContext.getContext("javax.security.auth.Subject.container");
				System.out.println(object);
			}catch(Exception e){
				e.printStackTrace();
			}
			return StandardNavigation.SUCCESS;
//		}catch(LoginException e){
//			log.error(e);
//		}
//		return StandardNavigation.FAILURE;
	}
	
	public Object doLogout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return StandardNavigation.SUCCESS;
	}
	
	public Object doForward() throws SQLException{
		new DatabaseHelper().doQuery(LoveDaemon.getDataSource(), "select * from sp");
		logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return StandardNavigation.ERROR;
	}
}
