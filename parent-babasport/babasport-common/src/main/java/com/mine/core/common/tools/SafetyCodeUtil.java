package com.mine.core.common.tools;

/**
 * 安全码工具类
 * 
 * @author Administrator
 *
 */
public class SafetyCodeUtil {

	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };

	private static final int[] number = { 0, 9 };

	private static final int[] lower = { 10, 35 };

	private static final int[] capital = { 36, 61 };

	private static final int[] onlychar = { 10, 61 };

	private static final int[] blend = { 0, 61 };

	public enum CodeLevel {
		number, lower, capital, onlychar, blend,
	}

	public static int[] getRangeByLevel(CodeLevel codeLevel) {
		switch (codeLevel) {
		case number:
			return number;
		case lower:
			return lower;
		case capital:
			return capital;
		case onlychar:
			return onlychar;
		default:
			return blend;
		}

	}

	public static String getSafetyCode(int digit, CodeLevel codeLevel) {
		if (digit > 0 && digit <= 32) {
			int[] range = getRangeByLevel(codeLevel);
			StringBuffer code = new StringBuffer();
			for (int i = 0; i < digit; i++) {
				int random = RandomUtil.getRandom(range[0], range[1]);
				code.append(chars[random]);
			}
			return code.toString();
		}
		return null;
	}

	public static String getImageSafetyCode() {
		return getSafetyCode(4, CodeLevel.blend);
	}

	public static String getPhoneSafetyCode() {
		return getSafetyCode(6, CodeLevel.number);
	}

	public static String getOrderNumber() {
		String time = DateUtil.date2Str(null, DateUtil.yyMMddHHmmssSSS);
		String ramdom = getSafetyCode(8, CodeLevel.number);
		return time + ramdom;
	}

}
