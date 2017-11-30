package com.mine.myboot.simple.common.tools;





public class PasswordUtil {

	/**
	 * 密码加密
	 * 
	 * @param password
	 * @return
	 */
/*	public static String encodePassword(String password) {
		// 加盐
		// password = "gxzcwefxcbergfd123456errttyyytytrnfzeczxcvertwqqcxvxb";
		// 1:MD5 算法
		String algorithm = "MD5";
		char[] encodeHex = null;
		try {
			// MD5加密
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			// 加密后
			byte[] digest = instance.digest(password.getBytes());
			//
			// 2:十六进制
			encodeHex = Hex.encodeHex(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(encodeHex);
	}*/
}
