package com.spstudio.love.interest.action;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.interest.entity.ClassifyStatBean;
import com.spstudio.love.interest.event.InterestQueryEvent;
import com.spstudio.love.interest.event.InterestQueryEvent.QueryMode;
import com.spstudio.love.interest.event.InterestQueryEventQualifier;
import com.spstudio.love.interest.nav.InterestNav;
import com.spstudio.love.system.bean.UserMenu;
import com.spstudio.love.system.qualifier.LoveLogged;

@ConversationScoped
@Named
public class InterestClassifyStatAction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3114791296915604338L;
	
	@Inject Conversation conversation;
	@Inject @InterestQueryEventQualifier Event<InterestQueryEvent> interestQueryEvent;
	@Inject UserMenu userMenu;
	@Inject @LoveLogged Logger log;
	
	private List<ClassifyStatBean> classifyStatListBean;
	
	public Object startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
			userMenu.setCurrentMenuNav(InterestNav.CLASSIFY_STAT_HOME);
			interestQueryEvent.fire(new InterestQueryEvent(QueryMode.LOAD_CLASSIFY_STAT_TECH_LIST));
		}
		return InterestNav.CLASSIFY_STAT_HOME;
	}
	
	public void endConversation() {
		if (!(conversation.isTransient())) {
			conversation.end();
		}
	}

	public List<ClassifyStatBean> getClassifyStatListBean() {
		return classifyStatListBean;
	}

	public void setClassifyStatListBean(List<ClassifyStatBean> classifyStatListBean) {
		this.classifyStatListBean = classifyStatListBean;
	}
	
}
