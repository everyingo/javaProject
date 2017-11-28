package com.mine.token;

import com.mine.model.Survey;

/**
 * ��������
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
	 * ��Ӱ�
	 * @param token
	 */
	public static void bindToken(SurveyToken token){
		l.set(token);
	}
	/**
	 * �����
	 * @param token
	 */
	public static void unbindToken(){
		l.remove();
	}
	/**
	 * ���token
	 * @param token
	 */
	public static SurveyToken getToken(){
		return l.get();
	}
}
