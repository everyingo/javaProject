package com.mine.service;

import com.mine.model.statistics.QuestionStatisticsModel;

public interface QuestionStatisticsService {

	/**
	 * �������ͳ��ģ��
	 * @param qid
	 * @return
	 */
	public QuestionStatisticsModel getQuestionStatistic(Integer qid);
}
