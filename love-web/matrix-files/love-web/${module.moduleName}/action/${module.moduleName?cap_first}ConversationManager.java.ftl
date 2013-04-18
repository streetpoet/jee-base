package com.spstudio.love.${module.moduleName}.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ${module.moduleName?cap_first}ConversationManager {
	
	@Inject ${module.moduleName?cap_first}Action ${module.moduleName}Action;
	
	public void endTotalConversation(){
		${module.moduleName}Action.endConversation();
	}
}
