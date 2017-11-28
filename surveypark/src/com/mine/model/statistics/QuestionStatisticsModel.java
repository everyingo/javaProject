package com.mine.model.statistics;

import java.util.ArrayList;
import java.util.List;

import com.mine.model.BaseEntity;
import com.mine.model.Question;

/**
 * 问题统计模型
 * @author Administrator
 *
 */
public class QuestionStatisticsModel extends BaseEntity{

	private static final long serialVersionUID = -5607688611402077295L;

	private Question question;//问题
	
	private Long count;//回答该问题的人数
	
	private List<OptionStatisticsModel> osms=new ArrayList<OptionStatisticsModel>();

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<OptionStatisticsModel> getOsms() {
		return osms;
	}

	public void setOsms(List<OptionStatisticsModel> osms) {
		this.osms = osms;
	}
	
	
}
