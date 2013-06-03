package com.spstudio.love.tbmarket.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class TbmarketConversationManager {
	
	@Inject TbmarketAction tbmarketAction;
	
	public void endTotalConversation(){
		tbmarketAction.endConversation();
	}
}
