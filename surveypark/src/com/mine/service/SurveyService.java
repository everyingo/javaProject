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
	 * 查询我的调查
	 * @param user
	 * @return
	 */
	public List<Survey> getMySurveys(User user);

	/**
	 * 新建调查
	 * @param user
	 * @return
	 */
	public Survey newSurvey(User user);

	/**
	 * 通过主键查询Survey
	 * @param sid
	 * @return
	 */
	public Survey getSurveyById(Serializable id);

	/**
	 * 通过主键查询Survey 和关联的子类
	 * @param sid
	 * @return
	 */
	public Survey getSurveyAndChildren(Serializable sid);

	/**
	 * 更新Survey
	 * @param model
	 */
	public void updateSurvey(Survey model);

	/**
	 * 删除调查 Survey 同时删除 page,question,answer 
	 * @param sid
	 */
	public void deleteSurvey(Integer sid);

	/**
	 * 清除调查
	 * @param sid
	 */
	public void clearSurvey(Integer sid);

	/**
	 * 打开/关闭 调查
	 * @param sid
	 */
	public void toggleStatus(Integer sid);

	/**
	 * 更新Survey logo的路径
	 * @param sid
	 * @param relativePath
	 */
	public void updateLogoPhotoPath(Integer sid, String relativePath);

	/**
	 * 查询user的调查 并加载出page
	 * @param user
	 * @return
	 */
	public List<Survey> getSurveyWithPage(User user);

	/**
	 * 查询 所有的 开放的调查
	 * @return
	 */
	public List<Survey> findAllOpenSurvey();

	/**
	 * 查询 sid调查下的 第一个页page
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid);

	/**
	 * 查询 调查底下的所有问题
	 * @param sid
	 * @return
	 */
	public List<Question> getQuestions(Integer sid);
	
	/**
	 * 查询 调查底下的所有Answer orderby uuid
	 * @param sid
	 * @return
	 */
	public List<Answer> getAnswers(Integer sid);
	
	

}
