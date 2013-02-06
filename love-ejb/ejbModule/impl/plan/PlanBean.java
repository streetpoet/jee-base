package impl.plan;

import interfaces.plan.IPlan;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.spstudio.love.plan.entity.Plan;
import com.spstudio.love.system.helper.DatabaseHelper;

@Stateless
public class PlanBean implements IPlan {

	@Inject DatabaseHelper helper;
	
	private static final String ADD_PLAN_SQL = "insert into f2_plan(" +
			"familyId, typeId, title, content, needMoney, amount, terminalDate, isRepeat, repeatUnitId, entry_user_id, entry_datetime) " +
			"values (?,?,?,?,?,?,?,?,?,?)";
	
	@Override
	public boolean addPlan(Plan plan) {
		Object[] params = new Object[]{
			plan.getFamilyId(),
			plan.getTypeId(),
			plan.getTitle(),
			plan.getContent(),
			plan.isNeedMoney(),
			plan.getAmount(),
			plan.getTerminalDate(),
			plan.isRepeat(),
			plan.getRepeatUnitId(),
			plan.getEntryUserId(),
			plan.getEntryDatetime()
		};
		return helper.doDMLOperation(ADD_PLAN_SQL, params) == 1;
	}

}
