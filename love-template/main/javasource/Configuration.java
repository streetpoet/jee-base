package javasource;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
	public static final String OUTPUT_FOLDER = "/Users/sp/Documents/work/git/jee-base/love-template/";
	public static Map<String, String> matrix = new HashMap<String, String>();
	public static final String MODULE_NAME = "Plan";
	
	/*
	 * EJB Singleton
	 */
	static String HTML_SELECT_TYPE_NAME = "UnitType";
	static String HTML_SELECT_SQL = "select id, unitName from f2_plan_unit order by id";
	
	static {
		matrix.put("\\$\\$000(.+?)\\$\\$000", MODULE_NAME);
		matrix.put("\\$\\$999(.+?)\\$\\$999", MODULE_NAME.toLowerCase());
		
		/*
		 * EJB Singleton
		 */
		matrix.put("\\$\\$001(.+?)\\$\\$001", HTML_SELECT_TYPE_NAME); // $$001PlanTypes$$001
		matrix.put("\\$\\$002(.+?)\\$\\$002", HTML_SELECT_TYPE_NAME.toUpperCase()); // $$002PlanTypes$$002
		matrix.put("\\$\\$003(.+?)\\$\\$003", HTML_SELECT_TYPE_NAME.substring(0, 1).toLowerCase() + HTML_SELECT_TYPE_NAME.substring(1)); // $$003planTypes$$003
		matrix.put("\\$\\$004(.+?)\\$\\$004", HTML_SELECT_SQL);
	}
}
