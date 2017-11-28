package com.mine.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Page;
import com.mine.model.Question;
import com.mine.service.QuestionService;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	
	private static final long serialVersionUID = 3491217886183904698L;
	
	@Resource(name="questionServiceImpl")
	private QuestionService questionService;
	//survey id 用于重定向
	private Integer sid;
	//page id 用于维持关联关系
	private Integer pid;
	
	private Integer qid;
	
	
	public Integer getQid() {
		return qid;
	}
	public void setQid(Integer qid) {
		this.qid = qid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 到达选择问题类型的页面
	 * @return
	 */
	public String toSelectQuestionType(){
		return "toSelectQuestionTypePage";
	}
	/**
	 * 到达设计问题页面
	 * @return
	 */
	public String toDesignQuestionPage(){
		
		return ""+model.getQuestionType();
	}
	/**
	 * 到达编辑问题页面
	 * @return
	 */
	public String toEditQuestionPage(){
		this.model=questionService.getEntity(qid);
		return ""+model.getQuestionType();
	}
	/**
	 * 保存/更新 Question
	 * @return 设计页面
	 */
	public String saveOrUpdateQuestion(){
		Page page=new Page();
		page.setId(pid);
		model.setPage(page);
		questionService.saveOrUpdateQuestion(model);
		return "toDesignSurveyAction";
	}
	/**
	 * 删除问题
	 * @return 设计页面
	 */
	public String deleteQuestion(){
		questionService.deleteQuestion(qid);
		return "toDesignSurveyAction";
	}

}
