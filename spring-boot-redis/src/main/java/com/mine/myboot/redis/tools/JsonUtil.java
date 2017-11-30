package com.mine.myboot.redis.tools;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * json 工具类 summer
 */
public class JsonUtil {

	/**
	 * object2Jsonstr
	 *
	 * @author summer
	 * @CT 2017-4-18下午4:21:02
	 */
	public static String object2Jsonstr(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * jsonstr2Object
	 *
	 * @author summer
	 * @CT 2017-4-18下午4:21:47
	 */
	public static <T> T jsonstr2Object(String jsonstr, Class<T> clazz) {
		try {
			return (T) JSON.parseObject(jsonstr, clazz);
		} catch (Exception e) {
			System.out.println("json error!");
		}
		return null;
	}

	/**
	 * jsonstr2List
	 *
	 * @return
	 * @author summer
	 * @CT 2017-4-18下午4:21:47
	 */
	public static <T> List<T> jsonstr2List(String jsonstr, Class<T> clazz) {
		try {
			return JSON.parseArray(jsonstr, clazz);
		} catch (Exception e) {
			System.out.println("json error!");
		}
		return null;
	}

	/**
	 * jsonstr2List
	 *
	 * @return
	 * @author summer
	 * @CT 2017-4-18下午4:21:47
	 */
	public static <T> List<T> jsonarr2List(JSONArray jsonstr, Class<T> clazz) {
		try {
			return jsonstr2List(jsonstr.toJSONString(), clazz);
		} catch (Exception e) {
			System.out.println("json error!");
		}
		return null;
	}

	/**
	 * jsonstr2Map
	 *
	 * @param jsonstr
	 * @return
	 * @author summer
	 * @CT 2017-4-18下午4:21:47
	 */
	public static Map<String, Object> jsonstr2Map(String jsonstr) {
		try {
			return JSON.parseObject(jsonstr);
		} catch (Exception e) {
			System.out.println("json error!");
		}
		return null;
	}

	/**
	 * jsonstr2JSONObject
	 *
	 * @param jsonstr
	 * @return
	 * @author summer
	 * @CT 2017-4-18下午4:21:47
	 */
	public static JSONObject jsonstr2JSONObject(String jsonstr) {
		try {
			return JSON.parseObject(jsonstr);
		} catch (Exception e) {
			System.out.println("json error!");
		}
		return null;
	}

}