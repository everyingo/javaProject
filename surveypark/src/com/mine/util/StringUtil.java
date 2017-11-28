package com.mine.util;

import java.util.UUID;

/**
 * �ַ���������
 * 
 * @author Administrator
 * 
 */
public class StringUtil {

	/**
	 * ���ַ�����ֳ��ַ�������
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
	 * �ж��ַ��������Ƿ����ĳ���ַ���
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
	 * ����ת�����ַ���
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
	 * ��ȡUUID
	 * @return
	 */
	public static String getUUID() {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * ��ȡstring
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
