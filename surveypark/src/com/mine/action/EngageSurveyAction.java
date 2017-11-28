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
 * �������
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
    //�������еĲ���
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
	 * �����������б�ҳ��
	 * 
	 * @return
	 */
	public String toEngageSurveyList() {
		this.surveys = surveyService.findAllOpenSurvey();
		return "engageSurveyListPage";
	}

	/**
	 * ��ȡlogo��ͼƬ
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
	 * �������
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
	 * ����������
	 * 
	 * @return
	 */
	public String doEngageSurvey() {
		String submitName = getSubmitName();
		// ��һҳ
		if (submitName.endsWith("pre")) {
			this.currPage = pageService.getPrePage(currPid);
			mergeParamsIntoSession();
			return "engageSurveyPage";
		}
		// ��һҳ
		else if (submitName.endsWith("next")) {
			this.currPage = pageService.getNextPage(currPid);
			mergeParamsIntoSession();
			return "engageSurveyPage";
		}
		// ���
		else if (submitName.endsWith("done")) {
			mergeParamsIntoSession();
			//�����
			List<Answer> answers=processAnswer();
			//���survey����
			SurveyToken token=new SurveyToken();
			token.setSurvey(getCurrSurvey());
			SurveyToken.bindToken(token);
			// ���
			AnswerService.saveAllAnswer(answers);
			clearSessionData();
			return "toEngageSurveyListAction";
		}
		// ȡ��
		else if (submitName.endsWith("exit")) {
			clearSessionData();
			return "toEngageSurveyListAction";
		}
		return "";
	}

	/**
	 * �����
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
		/**Ϊ ��ѡ���� ����answer */
		createMatriAnswer(matriAnswerMap,answers);
		return answers;
	}

	/**
	 * Ϊ ��ѡ���� ����answer
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
	 * ��þ���� qid
	 * @param key
	 * @return
	 */
	private Integer getMatriQid(String key) {
		return Integer.parseInt(key.substring(1,key.lastIndexOf("_")));
	}
	/**
	 * ���qid
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
	 * �ϲ�������session
	 */
	private void mergeParamsIntoSession() {
		Map<Integer, Map<String, String[]>> map = getAllParamsMap();
		map.put(currPid, paramsMap);
	}

	/**
	 * ��ȡsession�е����еĲ���
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, Map<String, String[]>> getAllParamsMap() {
		return (Map<Integer, Map<String, String[]>>) getSessionMap().get(
				SESSION_PARAMS_MAP);
	}

	/**
	 * ���session
	 */
	private void clearSessionData() {
		getSessionMap().remove(SESSION_PARAMS_MAP);
		getSessionMap().remove(SESSION_SURVEY);
	}

	/**
	 * ����ύ��ť������
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
	//��һҳ ��һҳ �Ļ��� radio|checkbox|select| 
	public String setTag(String name,String value,String tag){
    	Map<String,String[]> map=getAllParamsMap().get(currPage.getId());
    	String[] strs=map.get(name);
    	if(StringUtil.constains(strs,value)){
    		return tag;
    	}
    	return "";
    }
	//��һҳ ��һҳ �Ļ��� text 
	public String setText(String name){
		Map<String,String[]> map=getAllParamsMap().get(currPage.getId());
    	String[] strs=map.get(name);
    	if(ValidateUtil.isValidate(strs)){
    		return "value='"+strs[0]+"'";
    	}
		return "";
	}
	//ע��paramsMap
	public void setParameters(Map<String, String[]> arg0) {
		this.paramsMap = arg0;
	}
}
