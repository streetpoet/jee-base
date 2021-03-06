package interfaces.plan;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IPlanSingleton {
	
	public List<String[]> retrievePlanTypeList();
	
	public static class PlanType{
		public static final int INDEX_PLANTYPE_ID = 0;
		public static final int INDEX_PLANTYPE_NAME = 1;
	}
	
	public List<String[]> retrieveUnitTypeList();
	
	public static class UnitType{
		public static final int INDEX_UNITTYPE_ID = 0;
		public static final int INDEX_UNITTYPE_NAME = 1;
	}
}
