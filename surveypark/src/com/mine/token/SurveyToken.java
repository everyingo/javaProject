package com.mine.token;

import com.mine.model.Survey;

/**
 * 调查令牌
 * @author Administrator
 *
 */
public class SurveyToken {

	private static ThreadLocal<SurveyToken> l=new ThreadLocal<SurveyToken>();
	
	private Survey survey;

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	} 
	
	/**
	 * 添加绑定
	 * @param token
	 */
	public static void bindToken(SurveyToken token){
		l.set(token);
	}
	/**
	 * 解除绑定
	 * @param token
	 */
	public static void unbindToken(){
		l.remove();
	}
	/**
	 * 获得token
	 * @param token
	 */
	public static SurveyToken getToken(){
		return l.get();
	}
}
