package com.spstudio.love.system.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class LoveExceptionHandlerFactory extends
		ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;
	
	public LoveExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = parent.getExceptionHandler();
		return new LoveExceptionHandler(result); 
	}

}
