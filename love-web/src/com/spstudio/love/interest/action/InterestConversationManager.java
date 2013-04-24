package com.spstudio.love.interest.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class InterestConversationManager {
	
	@Inject InterestAction interestAction;
	@Inject InterestClassifyStatAction interestClassifyStatAction;
	
	public void endTotalConversation(){
		interestAction.endConversation();
		interestClassifyStatAction.endConversation();
	}
}
