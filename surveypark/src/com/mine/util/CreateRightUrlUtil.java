package com.mine.util;


import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mine.service.RightService;

public class CreateRightUrlUtil {

	public static void main(String[] args) throws Exception {
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		RightService rs=ac.getBean(RightService.class);
		
		ClassLoader loader=CreateRightUrlUtil.class.getClassLoader();
		URL url=loader.getResource("com/mine/action");
		File file=new File(url.toURI()); 
		File[] files=file.listFiles();
		for(File f:files){
			String fileName=f.getName();
			
			if(fileName.endsWith(".class") && !fileName.contains("BaseAction")){
				String simpleClassName=fileName.substring(0,fileName.indexOf(".class"));
				processUrl(simpleClassName,rs);
			}
		}
	}

	private static void processUrl(String simpleClassName, RightService rs) {
	    String pkg="com.mine.action";
	    try {
			Class<?> clazz= Class.forName(pkg+"."+simpleClassName);
			Method[] methods=clazz.getDeclaredMethods();
			String url="";
			for(Method m:methods){
				String mname=m.getName();
				Class<?> retype=m.getReturnType();
				Class<?>[] paramType=m.getParameterTypes();
				if(retype==String.class 
						&& !ValidateUtil.isValidate(paramType)
						&& Modifier.isPublic(m.getModifiers())){
					if(mname.equals("execute")){
						url="/"+simpleClassName;
					}else{
						url="/"+simpleClassName+"-"+mname;
					}
					
					rs.createRightByUrl(url);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
