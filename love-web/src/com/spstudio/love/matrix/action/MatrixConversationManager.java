package com.spstudio.love.matrix.action;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class MatrixConversationManager {
	
	@Inject MatrixAction matrixAction;
	
	public void endTotalConversation(){
		matrixAction.endConversation();
	}
}
