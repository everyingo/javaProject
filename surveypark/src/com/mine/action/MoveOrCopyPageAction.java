package com.mine.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.aware.UserAware;
import com.mine.model.Page;
import com.mine.model.Survey;
import com.mine.model.User;
import com.mine.service.PageService;
import com.mine.service.SurveyService;

/**
 * 移动/复制页面（page）Action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class MoveOrCopyPageAction extends BaseAction<Page> implements UserAware{

	
	private static final long serialVersionUID = 7210008836577280062L;
	
	@Resource(name="surveyServiceImpl")
	private SurveyService surveyService;
	
	@Resource(name="pageServiceImpl")
	private PageService pageService;
	
	//原页面id
	private Integer srcPid;
	
	//目标页id
	private Integer targPid;
	
	//移动/复制 成功后目标页 所在调查id
	private Integer sid;
	
	//之前0/之后1
	private Integer pos;
	
	private List<Survey> mySurveys;
	
	//当前用户
	public User user;
	
	public Integer getTargPid() {
		return targPid;
	}
	public void setTargPid(Integer targPid) {
		this.targPid = targPid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Integer getSrcPid() {
		return srcPid;
	}
	public void setSrcPid(Integer srcPid) {
		this.srcPid = srcPid;
	}
	public List<Survey> getMySurveys() {
		return mySurveys;
	}
	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	/**
	 * 到达移动/复制 页面
	 * @return
	 */
	public String toMoveOrCopyPage(){
		this.mySurveys=surveyService.getSurveyWithPage(user);
		return "toMoveOrCopyPagePage";
	}
	/**
	 * 移动/复制  page
	 * @return
	 */
	public String doMoveOrCopyPage(){
		pageService.MoveOrCopyPage(srcPid,targPid,pos);
		return "toDesignSurveyAction";
	}
	
	//注入User
	public void setUser(User user) {
	   this.user=user;
	}

}
