package com.spstudio.love.system.tool;

import org.jboss.security.auth.spi.Util;


public class PasswordGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PasswordGenerator().run();
	}
	
	public void run(){
		String pwd = Util.createPasswordHash("MD5", Util.BASE64_ENCODING, null, null, "213231");
		System.out.println(pwd);
	}

}
