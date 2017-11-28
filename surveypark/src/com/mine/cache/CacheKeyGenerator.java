package com.mine.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

import com.mine.util.StringUtil;

public class CacheKeyGenerator implements KeyGenerator{

	public Object generate(Object arg0, Method arg1, Object... arg2) {
		String className=arg0.getClass().getName();
		String mname=arg1.getName();
		String params=StringUtil.arr2str(arg2);
		String key=className+"."+mname+"("+params+")";
		System.out.println(key);
		return key;
	}

}
