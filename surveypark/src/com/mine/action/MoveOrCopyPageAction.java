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
 * �ƶ�/����ҳ�棨page��Action
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
	
	//ԭҳ��id
	private Integer srcPid;
	
	//Ŀ��ҳid
	private Integer targPid;
	
	//�ƶ�/���� �ɹ���Ŀ��ҳ ���ڵ���id
	private Integer sid;
	
	//֮ǰ0/֮��1
	private Integer pos;
	
	private List<Survey> mySurveys;
	
	//��ǰ�û�
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
	 * �����ƶ�/���� ҳ��
	 * @return
	 */
	public String toMoveOrCopyPage(){
		this.mySurveys=surveyService.getSurveyWithPage(user);
		return "toMoveOrCopyPagePage";
	}
	/**
	 * �ƶ�/����  page
	 * @return
	 */
	public String doMoveOrCopyPage(){
		pageService.MoveOrCopyPage(srcPid,targPid,pos);
		return "toDesignSurveyAction";
	}
	
	//ע��User
	public void setUser(User user) {
	   this.user=user;
	}

}
