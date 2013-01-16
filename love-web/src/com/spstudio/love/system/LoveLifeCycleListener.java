package com.spstudio.love.system;

import javax.faces.event.ComponentSystemEvent;
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
		if (event.getPhaseId().equals(PhaseId.RESTORE_VIEW) && event.getFacesContext().getViewRoot().getViewId().equals("/pages/product/overview.xhtml")){
			if (event.getFacesContext().isPostback()){
				event.getFacesContext().getViewRoot().getChildren().get(3).getChildren().get(0).getChildren().get(1).getChildren().get(2).setRendered(false);
			}
		}else if (event.getPhaseId().equals(PhaseId.APPLY_REQUEST_VALUES) && event.getFacesContext().getViewRoot().getViewId().equals("/pages/product/overview.xhtml")){
			event.getFacesContext().getViewRoot();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
//		System.out.println("beforePhase " + event.getPhaseId().toString());
//		if (event.getPhaseId().equals(PhaseId.APPLY_REQUEST_VALUES)){
//			try{
//				event.getFacesContext().getViewRoot().getChildren().get(3).getChildren().get(0).getChildren().get(1).getChildren().get(2).setRendered(true);
//				}catch(Exception e){}
//		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
