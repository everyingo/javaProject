package com.mine.service;

import com.mine.model.statistics.QuestionStatisticsModel;

public interface QuestionStatisticsService {

	/**
	 * 获得问题统计模型
	 * @param qid
	 * @return
	 */
	public QuestionStatisticsModel getQuestionStatistic(Integer qid);
}
