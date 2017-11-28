package com.mine.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


import com.mine.util.JsonUtil;
import com.mine.util.ValidateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("unchecked")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>,Preparable{

	
	private static final long serialVersionUID = -862532152497220133L;
	 
	protected T model;
	
	public BaseAction(){
		try {
			ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz=(Class<T>) type.getActualTypeArguments()[0];
			model=clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void prepare() throws Exception {
		
	}
	public  T getModel(){
		
		return model;
	}
	
	
	/**
	 * 获取HttpServletRequest
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		
		return ServletActionContext.getRequest(); 
	}
	/**
	 * 获取HttpServletResponse
	 * @return
	 */
	protected HttpServletResponse getRepose(){
		
		return ServletActionContext.getResponse();
	}
	/**
	 * 获取HttpServletResponse，并设好header
	 * @return
	 */
	protected HttpServletResponse getReposeAndSetHeader() {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		return response;
	}
	/**
	 * 获取HttpSession
	 * @return
	 */
	protected HttpSession getSeesion() {
		
		return this.getRequest().getSession();
	}
	/**
	 * 获取 ServletContext
	 * @return
	 */
	protected ServletContext getServletContext() {
		
		return ServletActionContext.getServletContext();
	}
	
	/**
	 * 获取参数集合
	 * @return
	 */
	protected Map<String, Object> getParameters() {
		ActionContext ac=ActionContext.getContext();
		return ac.getParameters();
	}
	/**
	 * 从参数集合中获取String 类型的数据
	 * @param key
	 * @return
	 */
	protected String getString(String key) {
		String[] strs=(String[]) this.getParameters().get(key);
		if(strs!=null && strs.length>0){
			return strs[0];
		}
		return null;
	}
	/**
	 * 从参数集合中获取Integer 类型的数据
	 * @param key
	 * @return
	 */
	protected Integer getInt(String key) {
		String[] strs=(String[]) this.getParameters().get(key);
		if(strs!=null && strs.length>0){
			try {
				Integer integer= Integer.parseInt(strs[0]);
				return integer;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}
	/**
	 * 从参数集合中获取Integer 类型的数据
	 * @param key
	 * @return
	 */
	protected Integer[] getInts(String key) {
		String[] strs=(String[]) this.getParameters().get(key);
		if(ValidateUtil.isValidate(strs)){
			Integer[] ints=new Integer[strs.length];
			try {
				 for (int i = 0; i < ints.length; i++) {
					 Integer integer= Integer.parseInt(strs[i]);
					 ints[i]=integer;
						
				 }
			} catch (NumberFormatException e) {
			}
			return ints;
		}
		return null;
	}
	/**
	 * 从参数集合中获取Integer 类型的数据
	 * @param key
	 * @return
	 */
	protected Long getLong(String key) {
		String[] strs=(String[]) this.getParameters().get(key);
		if(strs!=null && strs.length>0){
			try {
				Long lon= Long.parseLong(strs[0]);
				return lon;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}
	/**
	 * 从参数集合中获取Double 类型的数据
	 * @param key
	 * @return
	 */
	protected Double getDouble(String key) {
		String[] strs=(String[]) this.getParameters().get(key);
		if(strs!=null && strs.length>0){
			try {
				Double dou= Double.parseDouble(strs[0]);
				return dou;
			} catch (NumberFormatException e) {	
			}
		}
		return null;
	}
	/**
	 * 从参数集合中获取Date 类型的数据
	 * @param key
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected Date getDate(String key) {
		String[] strs=(String[]) this.getParameters().get(key);
		if(strs!=null && strs.length>0){
		try {
			  return new Date(strs[0]);
			} catch (Exception e) {
			  System.out.println("时间格式不正确！");
			}
		}
		return null;
	}
	/**
	 * 获取RequestMap map集合
	 * @return
	 */
	protected Map<String,Object> getRequestMap() {
		ActionContext ac=ActionContext.getContext();
		Map<String,Object> map=(Map<String, Object>) ac.get("request");
		return map;
	}
	/**
	 * 获取SessionMap map集合
	 * @return
	 */
	protected Map<String,Object> getSessionMap() {
		ActionContext ac=ActionContext.getContext();
		Map<String,Object> map=(Map<String, Object>) ac.getSession();
		return map;
	}
	/**
	 * 向RequestMap 集合中放入数据
	 * @param key
	 * @param value
	 */
	protected void pushRequestMap(String key,Object value) {
		this.getRequestMap().put(key, value);
	}
	/**
	 * 向SessionMap 集合中放入数据
	 * @param key
	 * @param value
	 */
	protected void pushSessionMap(String key,Object value) {
		this.getSessionMap().put(key, value);
	}
	
	/**
	 * 发送数据至调用者
	 * @param map
	 */
	protected void sendData(Map<String,Object> map) {
		HttpServletResponse response=getRepose();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter pw=null;
		try {
			pw = response.getWriter();
			pw.printf(JsonUtil.objectToJson(map));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
		
	}

}
