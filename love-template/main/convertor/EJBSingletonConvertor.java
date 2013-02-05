package convertor;

import java.util.Iterator;
import java.util.Map;

import javasource.Configuration;



public class EJBSingletonConvertor {
	public static String convert(String source){
		Map<String, String> map = Configuration.matrix;
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()){
			String key = it.next();
			if (key.equals("\\$\\$2(.+?)\\$\\$2") && source.contains("INDEX")){
				System.out.println("");
			}
			source = source.replaceAll(key, map.get(key));
		}
		return source;
	}
}
	