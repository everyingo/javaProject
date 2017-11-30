package com.mine.myboot.simple.common.tools;

import java.util.Collection;
import java.util.Map;

public class ValidateUtil {
	/**
	 * 校验字符串是否有效
	 *
	 * @param str
	 * @return
	 */
	public static boolean isValidate(String str) {
		return !(str == null || "".equals(str.trim()));
	}

	/**
	 * 校验字符串数组每一个元素是否有效
	 *
	 * @param strs
	 * @return
	 */
	public static boolean isValidate(String... strs) {
		if (strs != null && strs.length > 0) {
			for (String str : strs) {
				if (!isValidate(str))
					return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 校验集合是否有效
	 *
	 * @param col
	 * @return
	 */
	public static boolean isValidate(Collection<?> col) {
		return !(col == null || col.isEmpty());
	}

	/**
	 * 校验Map集合是否有效
	 *
	 * @param map
	 * @return
	 * @CT 2017-6-13下午4:46:41
	 */
	public static boolean isValidate(Map<?, ?> map) {
		return !(map == null || map.isEmpty());
	}

	/**
	 * 检查数组是否有效
	 *
	 * @param objs
	 * @return
	 */
	public static boolean isValidate(Object[] objs) {
		if (objs != null && objs.length > 0) {
			return true;
		}
		return false;
	}
}
