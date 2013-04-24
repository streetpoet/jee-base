package com.spstudio.love.interest.action;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.spstudio.love.interest.entity.MemberStatBean;
import com.spstudio.love.interest.event.InterestQueryEvent;
import com.spstudio.love.interest.event.InterestQueryEvent.QueryMode;
import com.spstudio.love.interest.event.InterestQueryEventQualifier;
import com.spstudio.love.interest.nav.InterestNav;
import com.spstudio.love.system.bean.UserMenu;
import com.spstudio.love.system.qualifier.LoveLogged;

@ConversationScoped
@Named
public class MemberStatAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3745769511604738369L;
	
	@Inject Conversation conversation;
	@Inject @InterestQueryEventQualifier Event<InterestQueryEvent> interestQueryEvent;
	@Inject UserMenu userMenu;
	@Inject @LoveLogged Logger log;
	
	private List<MemberStatBean> memberStatListBean;
	
	public Object startConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
			userMenu.setCurrentMenuNav(InterestNav.MEMBER_STAT_HOME);
			interestQueryEvent.fire(new InterestQueryEvent(QueryMode.LOAD_MEMBER_STAT_TECH_LIST));
		}
		return InterestNav.MEMBER_STAT_HOME;
	}
	
	public void endConversation() {
		if (!(conversation.isTransient())) {
			conversation.end();
		}
	}

	public List<MemberStatBean> getMemberStatListBean() {
		return memberStatListBean;
	}

	public void setMemberStatListBean(List<MemberStatBean> memberStatListBean) {
		this.memberStatListBean = memberStatListBean;
	}
	
}
