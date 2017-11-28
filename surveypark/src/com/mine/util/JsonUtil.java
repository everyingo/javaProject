package com.mine.util;

import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil
{

	/**
     * 将java对象转换成json字符�?
     *
     * @param javaObj
     * @return
     */
    public static String objectToJson(Object javaObj) {
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
    }
	
    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     *
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String jsonString, Class<T> pojoCalss) {
        if (jsonString == null || jsonString.trim().length() < 1) {
			return (T) new Object();
		}
    	Object pojo;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return (T) pojo;
    }
    
    /**
     * list变成json
     *
     * @param list
     * @return
     */
    public static <T> String listToJson(List<T> list) {
        JSONArray json;
        json = JSONArray.fromObject(list);
        return json.toString();
    }
    /**
     * json字符串转换成集合
     *
     * @param jsonString
     * @param pojoClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> jsonToList(String jsonString, Class<T> pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add((T) pojoValue);
        }
        return list;
    }
 
 
    
 
 
   
    
    
    
    
    
    
}