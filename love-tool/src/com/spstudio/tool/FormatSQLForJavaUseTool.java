package com.spstudio.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FormatSQLForJavaUseTool {
	
	public static void main(String args[]) throws Exception{
//		String currentPath = System.getProperty("user.dir");
//		String inputFileStr = currentPath + File.separator + "workspace" + File.separator + "sql-before.txt";
//		String outputFileStr = inputFileStr.substring(0, inputFileStr.lastIndexOf(File.separator)) + File.separator + "sql-after.txt";
		
		new FormatSQLForJavaUseTool().execute(args[0], args[1]);
	}
	
	public void execute(String inputFileStr, String outputFileStr) throws Exception{
		
		File inputFile = new File(inputFileStr);
		File outputFile = new File(outputFileStr);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
		
		String line = null;
		String temp = "";
		int index = 0;
		while ((line = br.readLine()) != null){
			temp = (index == 0 ? " \"" : "+\"") + line + " \"";
			bw.write(temp); System.out.println(temp);
			bw.newLine();System.out.println("\r\n");
			index ++;
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
