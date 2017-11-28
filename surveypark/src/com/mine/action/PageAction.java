package com.mine.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Page;
import com.mine.model.Survey;
import com.mine.service.PageService;

@Controller
@Scope("prototype")
public class PageAction extends BaseAction<Page> {


	private static final long serialVersionUID = 7155287065453163557L;
	
	@Resource(name="pageServiceImpl")
	private PageService pageService;
	
	private Integer sid;
	
	private Integer pid;
	
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
	 * 到达添加页面
	 * @return
	 */
	public String toAddPage(){
		
		return "addPagePage";
	}
	/**
	 * 到达编辑页面
	 * @return
	 */
	public String toEditPage(){
		this.model=pageService.getEntity(pid);
		return "editPagePage";
	}
	/**
	 * 添加/更新 page
	 * @return
	 */
	public String saveOrUpdatePage(){
		Survey survey=new Survey();
		survey.setId(sid);
		model.setSurvey(survey);
		pageService.saveOrUpdatePage(model);
	    return "toDesignSurveyAction";
	}
	
	/**
	 * 删除Page 同时删除 Question,Answer 
	 * @return
	 */
	public String deletePage(){
		pageService.deletePage(pid);
		return "toDesignSurveyAction";
	}

}
