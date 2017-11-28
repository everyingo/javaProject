package com.mine.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Answer;
import com.mine.service.AnswerService;
import com.mine.util.StringUtil;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private BaseDao<Answer> answerDao;
	
	/**
	 * ÅúÁ¿±£´æanswer
	 * @param answers
	 */
	public void saveAllAnswer(List<Answer> answers){
		String uuid=StringUtil.getUUID();
		Date now=new Date(); 
		for(Answer a :answers){
			a.setUuid(uuid);
			a.setAnswerTime(now);
			answerDao.saveEntity(a);
		}
	}
}
