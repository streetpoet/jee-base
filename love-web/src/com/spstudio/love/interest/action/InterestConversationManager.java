package com.spstudio.love.interest.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class InterestConversationManager {
	
	@Inject InterestAction interestAction;
	@Inject InterestClassifyStatAction interestClassifyStatAction;
	@Inject MemberStatAction memberStatAction;
	@Inject InterestChartStatAction interestChartStatAction;
	
	public void endTotalConversation(){
		interestAction.endConversation();
		interestClassifyStatAction.endConversation();
		memberStatAction.endConversation();
		interestChartStatAction.endConversation();
	}
}
