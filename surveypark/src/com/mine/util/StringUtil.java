package com.mine.util;

import java.util.UUID;

/**
 * 字符串工具类
 * 
 * @author Administrator
 * 
 */
public class StringUtil {

	/**
	 * 将字符串拆分成字符串数组
	 * 
	 * @param str
	 * @param regx
	 * @return
	 */
	public static String[] string2Arr(String str, String regx) {
		if (ValidateUtil.isValidate(str)) {
			return str.split(regx);
		}
		return null;
	}

	/**
	 * 判断字符串数组是否包含某个字符串
	 * 
	 * @param strs
	 * @param value
	 * @return
	 */
	public static boolean constains(String[] strs, String value) {
		if (ValidateUtil.isValidate(strs)) {
			for (String str : strs) {
				if (str.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 数组转换成字符串
	 * @param objs
	 * @return
	 */
	public static String arr2str(Object[] objs) {
		String str="";
		if(ValidateUtil.isValidate(objs)){
			for(Object o:objs){
				str=str+o.toString()+",";
			}
			str=str.substring(0,str.length()-1);
		}
		return str;
	}

	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 截取string
	 * @param str
	 * @return
	 */
	public static String subString(String str){
		if(ValidateUtil.isValidate(str)){
			return str.substring(0,15);
		}
		return "";
	}
}
