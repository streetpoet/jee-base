package interfaces.plan;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IPlanSingleton {
	
	public List<String[]> retrievePlanTypes();
	public List<String[]> retrievePlanUnits();
	
	public static class PlanType{
		public static final int INDEX_TYPE_ID = 0;
		public static final int INDEX_TYPE_NAME = 1;
	}
	
	public static class PlanUnit{
		public static final int INDEX_UNIT_ID = 0;
		public static final int INDEX_UNIT_NAME = 1;
	}
}
