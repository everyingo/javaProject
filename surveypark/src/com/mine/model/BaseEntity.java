package com.mine.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -4274980286509573836L;

	/**
	 * 重写toString 方便记录日志 时记录类的详细信息
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Class<?> clazz = this.getClass();
		try {
			String className = clazz.getName();
			sb.append(className + ":{");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				Class<?> ftype = f.getType();
				String fname = f.getName();
				f.setAccessible(true);
				Object fvalue = f.get(this);
				if((ftype.isPrimitive() 
						||ftype==Short.class
						||ftype==Integer.class
						||ftype==Long.class
						||ftype==Float.class
						||ftype==Double.class
						||ftype==String.class
						||ftype==Character.class
						||ftype==Boolean.class)
						&& !Modifier.isStatic(f.getModifiers())){
					sb.append(" "+fname);
					sb.append(":");
					sb.append(" "+fvalue);
				}
			}
			sb.append("}");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
