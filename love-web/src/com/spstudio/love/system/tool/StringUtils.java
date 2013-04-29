package com.spstudio.love.system.tool;

import java.io.File;

public class StringUtils {
	
	/**
	 * Remove '.' in path, and convert to File.separator
	 * @param originalPath original path like '/user/xx/a.b.c/12.abc.java'
	 * @return converted string, like '/user/xx/a/b/c/12.abc.java'
	 */
	public static String convertFilePath(String originalPath){
		String[] pathArray = originalPath.split(File.separator);
		for (int i = 0; i < pathArray.length; i ++){
			String part = pathArray[i];
			if (part.indexOf(".") > 0 && !originalPath.contains(".settings")){
				if (! (i == pathArray.length - 1)){
					String newPart = part.replaceAll("\\.", File.separator);
					originalPath = originalPath.replaceAll(part, newPart);
				}
			}
		}
		return originalPath;
	}
}
