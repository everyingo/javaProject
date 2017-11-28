package com.mine.service;

import com.mine.model.Question;

public interface QuestionService extends BaseService<Question>{

	/**
	 * 保存/更新 Question
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 删除 Question 同时删除 answer
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

}
