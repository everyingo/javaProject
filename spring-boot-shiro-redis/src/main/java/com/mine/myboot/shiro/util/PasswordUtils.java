package com.mine.myboot.shiro.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.mine.myboot.shiro.pojo.User;

public class PasswordUtils {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static int hashIterations = 1;

	public static void encryptPassword(User user) {
		String salt = randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getUsername() + salt), hashIterations).toHex();
		// String newPassword = new SimpleHash(algorithmName,
		// user.getPassword()).toHex();
		user.setPassword(newPassword);

	}

	public static void main(String[] args) {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		encryptPassword(user);
		System.out.println(user);
	}
}
