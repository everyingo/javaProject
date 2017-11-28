package com.mine.model;

import java.util.HashSet;
import java.util.Set;

public class Page extends BaseEntity{

	private static final long serialVersionUID = -8190167297212863831L;

	private Integer id;
	
	private String title="未命名";
	
	private String description;
	
	private Float orderno;//排序字段
	
	//transient:临时的 jvm识别该字段 停止对象的序列化
	private transient Survey survey;
	private Set<Question> questions=new HashSet<Question>();

	
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.orderno=new Float(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getOrderno() {
		return orderno;
	}

	public void setOrderno(Float orderno) {
		this.orderno = orderno;
	}
	
}
