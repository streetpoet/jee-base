package interfaces.plan;

import javax.ejb.Remote;

import com.spstudio.love.plan.entity.Plan;

@Remote
public interface IPlan {
	public boolean addPlan(Plan Plan);
}
