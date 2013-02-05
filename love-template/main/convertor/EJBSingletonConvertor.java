package convertor;

import java.Configuration;



public class EJBSingletonConvertor {
	public static String convert(String source){
		source = source.replaceAll("\\$\\$(.+?)\\$\\$", Configuration.MODULE_NAME);
		return source;
	}
}
	