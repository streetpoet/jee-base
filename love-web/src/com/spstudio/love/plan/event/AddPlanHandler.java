package com.spstudio.love.plan.event;

import interfaces.plan.IPlan;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.spstudio.love.plan.entity.Plan;
import com.spstudio.love.plan.qualifier.PlanQualifier;
import com.spstudio.love.plan.qualifier.PlanRemoteBean;
import com.spstudio.love.system.bean.UserInfo;
import com.spstudio.love.system.qualifier.LoveTrace;
import com.spstudio.love.system.qualifier.UserInfoQualifier;

@Dependent
public class AddPlanHandler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -988405636819038144L;
	
	@Inject @PlanQualifier Plan plan;
	@Inject @PlanRemoteBean IPlan planRemoteBean;
	@Inject @UserInfoQualifier UserInfo userInfo;

	@LoveTrace
	public void addPlan(@Observes @AddPlanEventQualifier AddPlanEvent event){
		
		plan.setFamilyId(userInfo.getFamilyId());
		plan.setNeedMoney(plan.getAmount() != 0);
		plan.setRepeat(plan.getTypeId() == 2);
		plan.setEntryUserId(userInfo.getUserId());
		plan.setEntryDatetime(new Date());
		Plan p = plan.clone();
		boolean result = planRemoteBean.addPlan(p);
		FacesContext.getCurrentInstance().addMessage(
				FacesMessage.FACES_MESSAGES, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, result ? "success" : "fail", ""));
		plan.clear();
	}
}
