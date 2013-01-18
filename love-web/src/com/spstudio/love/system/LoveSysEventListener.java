package com.spstudio.love.system;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import org.jboss.logging.Logger;

public class LoveSysEventListener implements SystemEventListener {
	
	Logger log = Logger.getLogger(LoveSysEventListener.class);
	
	@Override
	public boolean isListenerForSource(Object arg0) {
		return true;
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		log.info("invoke PostConstructApplicationEvent");
	}

}
