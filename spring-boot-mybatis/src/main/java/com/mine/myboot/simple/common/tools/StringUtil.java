package com.mine.myboot.simple.common.tools;

import java.util.UUID;

public class StringUtil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
