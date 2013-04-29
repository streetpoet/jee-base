package ${project.packageString}.system.listener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import org.jboss.logging.Logger;

public class ${project.projectCode?cap_first}SysEventListener implements SystemEventListener {
	
	Logger log = Logger.getLogger(${project.projectCode?cap_first}SysEventListener.class);
	
	@Override
	public boolean isListenerForSource(Object arg0) {
		return true;
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		log.info("invoke PostConstructApplicationEvent");
	}

}
