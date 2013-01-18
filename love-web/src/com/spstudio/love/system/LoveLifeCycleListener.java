package com.spstudio.love.system;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

public class LoveLifeCycleListener implements javax.faces.event.PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6648193269219758508L;

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
