package interfaces.$$999plan$$999;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface I$$000Plan$$000Singleton {
	
	public List<String[]> retrieve$$001PlanType$$001List();
	
	public static class $$001PlanType$$001{
		public static final int INDEX_$$002PlanType$$002_ID = 0;
		public static final int INDEX_$$002PlanType$$002_NAME = 1;
	}
}
