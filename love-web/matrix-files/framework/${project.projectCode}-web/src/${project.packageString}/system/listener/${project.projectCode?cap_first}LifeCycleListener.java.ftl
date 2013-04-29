package ${project.packageString}.system.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

public class ${project.projectCode?cap_first}LifeCycleListener implements javax.faces.event.PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -${uid()}L;

	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("afterPhase " + event.getPhaseId().toString());
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("beforePhase " + event.getPhaseId().toString());
	}
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
