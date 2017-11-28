package com.mine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Answer;
import com.mine.model.Question;
import com.mine.service.QuestionService;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService{

	@Autowired
	private BaseDao<Question> questionDao;
	
	@Autowired
	private BaseDao<Answer> answerDao;
	/**
	 * ±£´æ/¸üÐÂ Question
	 * @param model
	 */
	public void saveOrUpdateQuestion(Question model){
		questionDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * É¾³ý Question Í¬Ê±É¾³ý answer
	 * @param qid
	 */
	public void deleteQuestion(Integer qid){
		//1.É¾³ýanswer
		String hql="delete from Answer a where a.questionId=?";
		answerDao.batchEntityByHql(hql, qid);
		//2.É¾³ýquestion
		hql="delete from Question q where q.id=?";
		questionDao.batchEntityByHql(hql, qid);
	}
}
