package com.mine.util;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class ValidateUtil {

	/**
	 * У���ַ����Ƿ���Ч
	 * @param str
	 * @return
	 */
	public static boolean isValidate(String str){
		return !(str==null || "".equals(str.trim()));
	}
	
	/**
	 * У�鼯���Ƿ���Ч
	 * @param col
	 * @return
	 */
	public static boolean isValidate(Collection col){
		return !(col==null || col.isEmpty());
	}

	/**
	 * ��������Ƿ���Ч
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
