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
	//survey id �����ض���
	private Integer sid;
	//page id ����ά�ֹ�����ϵ
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
	 * ����ѡ���������͵�ҳ��
	 * @return
	 */
	public String toSelectQuestionType(){
		return "toSelectQuestionTypePage";
	}
	/**
	 * �����������ҳ��
	 * @return
	 */
	public String toDesignQuestionPage(){
		
		return ""+model.getQuestionType();
	}
	/**
	 * ����༭����ҳ��
	 * @return
	 */
	public String toEditQuestionPage(){
		this.model=questionService.getEntity(qid);
		return ""+model.getQuestionType();
	}
	/**
	 * ����/���� Question
	 * @return ���ҳ��
	 */
	public String saveOrUpdateQuestion(){
		Page page=new Page();
		page.setId(pid);
		model.setPage(page);
		questionService.saveOrUpdateQuestion(model);
		return "toDesignSurveyAction";
	}
	/**
	 * ɾ������
	 * @return ���ҳ��
	 */
	public String deleteQuestion(){
		questionService.deleteQuestion(qid);
		return "toDesignSurveyAction";
	}

}
