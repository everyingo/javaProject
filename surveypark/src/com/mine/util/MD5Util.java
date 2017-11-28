package com.mine.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * 采用MD5加密解密
 * @author wwlg
 * @datetime 2015-01-21
 */
public class MD5Util {
	
	
	private static Logger log = Logger.getLogger(MD5Util.class.getName());

	/***
	 * MD5加码 生成32位md5�?
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			log.error("md5加密错误", e);
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = md5Bytes[i] & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行�?��加密，两次解�?
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

}
