package com.mine.service;

import com.mine.model.Question;

public interface QuestionService extends BaseService<Question>{

	/**
	 * ����/���� Question
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * ɾ�� Question ͬʱɾ�� answer
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

}
