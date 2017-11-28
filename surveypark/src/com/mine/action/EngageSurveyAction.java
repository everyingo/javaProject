package com.mine.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Answer;
import com.mine.model.Page;
import com.mine.model.Survey;
import com.mine.service.AnswerService;
import com.mine.service.PageService;
import com.mine.service.SurveyService;
import com.mine.token.SurveyToken;
import com.mine.util.StringUtil;
import com.mine.util.ValidateUtil;

/**
 * 参与调查
 * 
 * @author Administrator
 * 
 */
@Controller
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements
		ParameterAware {

	private static final long serialVersionUID = -7557469919728586359L;

	private static final String SESSION_SURVEY = "session_survey";
	private static final String SESSION_PARAMS_MAP = "session_params_map";

	@Resource(name = "surveyServiceImpl")
	private SurveyService surveyService;

	@Resource(name = "pageServiceImpl")
	private PageService pageService;
	
	@Resource(name="answerServiceImpl")
	private AnswerService AnswerService;

	private List<Survey> surveys;

	private Page currPage;

	private Integer currPid;

	private Integer sid;
    //接受所有的参数
	private Map<String, String[]> paramsMap;

	public Integer getCurrPid() {
		return currPid;
	}

	public void setCurrPid(Integer currPid) {
		this.currPid = currPid;
	}

	public Map<String, String[]> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, String[]> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 到达参与调查列表页面
	 * 
	 * @return
	 */
	public String toEngageSurveyList() {
		this.surveys = surveyService.findAllOpenSurvey();
		return "engageSurveyListPage";
	}

	/**
	 * 获取logo的图片
	 * 
	 * @param path
	 * @return
	 */
	public String getImageUrl(String path) {
		if (path != null) {
			String absPath = getServletContext().getRealPath(path);
			File file = new File(absPath);
			if (file.exists()) {
				return path;
			}
		}
		return "images/click.png";
	}

	/**
	 * 参与调查
	 * 
	 * @return
	 */
	public String entry() {
		this.currPage = surveyService.getFirstPage(sid);
		getSessionMap().put(SESSION_SURVEY, surveyService.getSurveyById(sid));
		getSessionMap().put(SESSION_PARAMS_MAP,
				new HashMap<Integer, Map<String, String[]>>());
		return "engageSurveyPage";
	}

	/**
	 * 处理参与调查
	 * 
	 * @return
	 */
	public String doEngageSurvey() {
		String submitName = getSubmitName();
		// 上一页
		if (submitName.endsWith("pre")) {
			this.currPage = pageService.getPrePage(currPid);
			mergeParamsIntoSession();
			return "engageSurveyPage";
		}
		// 下一页
		else if (submitName.endsWith("next")) {
			this.currPage = pageService.getNextPage(currPid);
			mergeParamsIntoSession();
			return "engageSurveyPage";
		}
		// 完成
		else if (submitName.endsWith("done")) {
			mergeParamsIntoSession();
			//处理答案
			List<Answer> answers=processAnswer();
			//添加survey令牌
			SurveyToken token=new SurveyToken();
			token.setSurvey(getCurrSurvey());
			SurveyToken.bindToken(token);
			// 入库
			AnswerService.saveAllAnswer(answers);
			clearSessionData();
			return "toEngageSurveyListAction";
		}
		// 取消
		else if (submitName.endsWith("exit")) {
			clearSessionData();
			return "toEngageSurveyListAction";
		}
		return "";
	}

	/**
	 * 处理答案
	 * @return
	 */
	private List<Answer> processAnswer() {
		List<Answer> answers=new ArrayList<Answer>();
		Answer a=null;
		Map<Integer,String> matriAnswerMap=new HashMap<Integer,String>();
		Map<Integer,Map<String,String[]>> allMap=getAllParamsMap();
		for (Map<String,String[]> map :allMap.values()) {
			for(Entry<String,String[]> entry:map.entrySet()){
				String key=entry.getKey();
				String[] value=entry.getValue();
				if(key.startsWith("q")){
					if(!key.contains("_") && !key.contains("other")){
						a=new Answer();
						a.setAnswerIds(StringUtil.arr2str(value));
						a.setOtherAnswer(StringUtil.arr2str(map.get(key+"other")));
						a.setQuestionId(getQid(key));//qid
						a.setSurveyId(getCurrSurvey().getId());//sid
						answers.add(a);
					}else if(key.contains("_")){
						Integer radioQid=getMatriQid(key);
						String oldValue =matriAnswerMap.get(radioQid);
						if(oldValue==null){
							matriAnswerMap.put(radioQid, StringUtil.arr2str(value));
						}else{
							matriAnswerMap.put(radioQid, oldValue+","+StringUtil.arr2str(value));
						}
					}
				}
			}
		}
		/**为 单选矩阵 创建answer */
		createMatriAnswer(matriAnswerMap,answers);
		return answers;
	}

	/**
	 * 为 单选矩阵 创建answer
	 */
	private void createMatriAnswer(Map<Integer, String> matriAnswerMap,List<Answer> answers) {
		Answer a=null;
		for (Entry<Integer, String> entry:matriAnswerMap.entrySet()) {
			Integer key=entry.getKey();
			String value=entry.getValue();
			a=new Answer();
			a.setAnswerIds(value);
			a.setQuestionId(key);//qid
			a.setSurveyId(getCurrSurvey().getId());//sid
			answers.add(a);
		}
		
	}
	/**
	 * 获得矩阵的 qid
	 * @param key
	 * @return
	 */
	private Integer getMatriQid(String key) {
		return Integer.parseInt(key.substring(1,key.lastIndexOf("_")));
	}
	/**
	 * 获得qid
	 * @return
	 */
	private Integer getQid(String key) {
		return Integer.parseInt(key.substring(1,key.length()));
	}
	/**
	 * 
	 * @return
	 */
	private Survey getCurrSurvey() {
		return (Survey) getSessionMap().get(SESSION_SURVEY);
	}
	/**
	 * 合并参数到session
	 */
	private void mergeParamsIntoSession() {
		Map<Integer, Map<String, String[]>> map = getAllParamsMap();
		map.put(currPid, paramsMap);
	}

	/**
	 * 获取session中的所有的参数
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>) getSessionMap().get(
				SESSION_PARAMS_MAP);
	}

	/**
	 * 清空session
	 */
	private void clearSessionData() {
		getSessionMap().remove(SESSION_PARAMS_MAP);
		getSessionMap().remove(SESSION_SURVEY);
	}

	/**
	 * 获得提交按钮的名称
	 * 
	 * @return
	 */
	private String getSubmitName() {
		for (String key : getParameters().keySet()) {
			if (key.startsWith("submit_")) {
				return key;
			}
		}
		return "";
	}
	//上一页 下一页 的回显 radio|checkbox|select| 
	public String setTag(String name,String value,String tag){
    	Map<String,String[]> map=getAllParamsMap().get(currPage.getId());
    	String[] strs=map.get(name);
    	if(StringUtil.constains(strs,value)){
    		return tag;
    	}
    	return "";
    }
	//上一页 下一页 的回显 text 
	public String setText(String name){
		Map<String,String[]> map=getAllParamsMap().get(currPage.getId());
    	String[] strs=map.get(name);
    	if(ValidateUtil.isValidate(strs)){
    		return "value='"+strs[0]+"'";
    	}
		return "";
	}
	//注入paramsMap
	public void setParameters(Map<String, String[]> arg0) {
		this.paramsMap = arg0;
	}
}
