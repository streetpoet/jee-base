package com.spstudio.love.plan.action;

import interfaces.plan.IPlanSingleton;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.spstudio.love.plan.event.AddPlanEvent;
import com.spstudio.love.plan.event.AddPlanEventQualifier;
import com.spstudio.love.plan.qualifier.PlanSingleRemoteBean;

@Model
public class PlanAction {
	
	@Inject @PlanSingleRemoteBean IPlanSingleton planSingleton;
	@Inject @AddPlanEventQualifier Event<AddPlanEvent> addPlanEvent;
	
	public List<SelectItem> getPlanTypesItems() {
		List<String[]> list = planSingleton.retrievePlanTypeList();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				selectItems.add(new SelectItem(data[IPlanSingleton.PlanType.INDEX_PLANTYPE_ID], data[IPlanSingleton.PlanType.INDEX_PLANTYPE_NAME]));
			}
		}
		return selectItems;
	}
	
	public List<SelectItem> getPlanUnitItems() {
		List<String[]> list = planSingleton.retrieveUnitTypeList();
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		if (list != null && list.size() != 0){
			for (String[] data: list){
				selectItems.add(new SelectItem(data[IPlanSingleton.UnitType.INDEX_UNITTYPE_ID], data[IPlanSingleton.UnitType.INDEX_UNITTYPE_NAME]));
			}
		}
		return selectItems;
	}
	
	/**
	 * Add New Plan
	 */
	public void createPlan() {
		addPlanEvent.fire(new AddPlanEvent());
	}
	
}
