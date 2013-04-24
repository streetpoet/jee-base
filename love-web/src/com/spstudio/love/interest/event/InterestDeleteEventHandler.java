package com.spstudio.love.interest.event;

import interfaces.interest.IInterest;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.spstudio.love.interest.entity.TechSelectBean;
import com.spstudio.love.interest.event.InterestQueryEvent.QueryMode;
import com.spstudio.love.interest.qualifier.InterestRemoteBean;
import com.spstudio.love.interest.qualifier.TechSelectBeanQualifier;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class InterestDeleteEventHandler implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -38643936817967L;
	
	@Inject @TechSelectBeanQualifier TechSelectBean techSelectBean;
	@Inject @InterestRemoteBean IInterest interestRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;
	@Inject @InterestQueryEventQualifier Event<InterestQueryEvent> interestQueryEvent;

	public void deleteTechSelectBean(@Observes @InterestDeleteEventQualifier InterestDeleteEvent event){
		TechSelectBean bean = techSelectBean.clone();
		bean.setUserId(userInfo.getUserId());
		interestRemoteBean.deleteTechSelectBean(bean);
		interestQueryEvent.fire(new InterestQueryEvent(QueryMode.LOAD_LIKED_TECH_LIST));
	}
}
