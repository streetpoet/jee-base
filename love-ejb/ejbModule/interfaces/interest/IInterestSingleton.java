package interfaces.interest;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IInterestSingleton {
	
	public List<String[]> retrieveTechClassifyList();
	
	public static class TechClassify{
		public static final int INDEX_ID = 0;
		public static final int INDEX_TECH_CLASS_1_NAME = 1;
		public static final int INDEX_CLASS_2_ID = 2;
		public static final int INDEX_TECH_CLASS_2_NAME = 3;
	}

}
