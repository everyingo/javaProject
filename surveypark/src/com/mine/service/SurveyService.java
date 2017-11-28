package com.mine.service;

import java.io.Serializable;
import java.util.List;

import com.mine.model.Answer;
import com.mine.model.Page;
import com.mine.model.Question;
import com.mine.model.Survey;
import com.mine.model.User;

public interface SurveyService{

	/**
	 * ��ѯ�ҵĵ���
	 * @param user
	 * @return
	 */
	public List<Survey> getMySurveys(User user);

	/**
	 * �½�����
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);

	/**
	 * ͨ��������ѯSurvey
	 * @param sid
	 * @return
	 */
	public Survey getSurveyById(Serializable id);

	/**
	 * ͨ��������ѯSurvey �͹���������
	 * @param sid
	 * @return
	 */
	public Survey getSurveyAndChildren(Serializable sid);

	/**
	 * ����Survey
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * ɾ������ Survey ͬʱɾ�� page,question,answer 
	 * @param sid
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * �������
	 * @param sid
	 */
	public void clearSurvey(Integer sid);

	/**
	 * ��/�ر� ����
	 * @param sid
	 */
	public void toggleStatus(Integer sid);

	/**
	 * ����Survey logo��·��
	 * @param sid
	 * @param relativePath
	 */
	public void updateLogoPhotoPath(Integer sid, String relativePath);

	/**
	 * ��ѯuser�ĵ��� �����س�page
	 * @param user
	 * @return
	 */
	public List<Survey> getSurveyWithPage(User user);

	/**
	 * ��ѯ ���е� ���ŵĵ���
	 * @return
	 */
	public List<Survey> findAllOpenSurvey();

	/**
	 * ��ѯ sid�����µ� ��һ��ҳpage
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * ��ѯ ������µ���������
	 * @param sid
	 * @return
	 */
	public List<Question> getQuestions(Integer sid);
	
	/**
	 * ��ѯ ������µ�����Answer orderby uuid
	 * @param sid
	 * @return
	 */
	public List<Answer> getAnswers(Integer sid);
	
	

}
