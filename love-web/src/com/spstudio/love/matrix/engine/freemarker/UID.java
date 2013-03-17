package com.spstudio.love.matrix.engine.freemarker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class UID implements TemplateMethodModel {

	public static final String UID = "uid";
	
	@Override
	public Object exec(List list) throws TemplateModelException {
		String className = System.nanoTime() + "";
		if (list != null && list.size() == 1) {
			className = (String) list.get(0);
		}
		byte[] buf = MD5((className + System.nanoTime()).getBytes());
		String tmp = "";
		for (int i = 0; i < buf.length; i++)
			tmp = tmp + byteToHex(buf[i], 2);
		return Long.valueOf(tmp.toUpperCase().substring(20), 16).toString();
	}

	public static byte[] MD5(byte[] value) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(value);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byteToHex(byte value, int minlength) {
		String s = Integer.toHexString(value & 0xff);
		if (s.length() < minlength) {
			for (int i = 0; i < (minlength - s.length()); i++)
				s = "0" + s;
		}
		return s;
	}

}
