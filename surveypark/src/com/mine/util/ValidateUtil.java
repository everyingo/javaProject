package com.mine.util;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class ValidateUtil {

	/**
	 * 校验字符串是否有效
	 * @param str
	 * @return
	 */
	public static boolean isValidate(String str){
		return !(str==null || "".equals(str.trim()));
	}
	
	/**
	 * 校验集合是否有效
	 * @param col
	 * @return
	 */
	public static boolean isValidate(Collection col){
		return !(col==null || col.isEmpty());
	}

	/**
	 * 检查数组是否有效
	 * @param strs
	 * @return
	 */
	public static boolean isValidate(Object[] objs) {
		if(objs!=null && objs.length>0){
			return true;
		}
		return false;
	}
}
