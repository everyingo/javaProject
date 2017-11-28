package com.mine.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Answer;
import com.mine.model.Page;
import com.mine.model.Question;
import com.mine.model.Survey;
import com.mine.model.User;
import com.mine.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{

	@Autowired
	private BaseDao<Survey> surveyDao;
	
	@Autowired
	private BaseDao<Page> pageDao;
	
	@Autowired
	private BaseDao<Question> questionDao;
	
	@Autowired
	private BaseDao<Answer> answerDao;
	/**
	 *  ��ѯ�ҵĵ���
	 */
	public List<Survey> getMySurveys(User user) {
		String hql="From Survey s where s.user.id=?";
		return surveyDao.findEntityByHql(hql, user.getId());
	}

	/**
	 * �½�����
	 */
	public Survey newSurvey(User user) {
		
		Survey survey=new Survey();
		Page page=new Page();
		survey.setUser(user);
		page.setSurvey(survey);
		surveyDao.saveEntity(survey);
		pageDao.saveEntity(page);
		survey.getPages().add(page);
		return survey;
	}

	/**
	 * ͨ��������ѯSurvey
	 */
	public Survey getSurveyById(Serializable id) {
		
		return surveyDao.getEntity(id);
	}

	/**
	 * ͨ��������ѯSurvey �͹���������
	 */
	public Survey getSurveyAndChildren(Serializable sid) {
		Survey survey=this.getSurveyById(sid);
		for (Page page : survey.getPages()) {
			 page.getQuestions().isEmpty();
		}
		return survey;
	}

	/**
	 * ����Survey
	 */
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	/**
	 *  ɾ������ Survey ͬʱɾ�� page,question,answer
	 */
	public void deleteSurvey(Integer sid) {
        //1.ɾ�� answer
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHql(hql, sid);
		//2.ɾ��question
		hql="delete from Question q where q.page.id in (select p.id from Page p where p.survey.id=?)";
		questionDao.batchEntityByHql(hql, sid);
		//3.ɾ��page
		hql="delete from Page p where p.survey.id=?";
		pageDao.batchEntityByHql(hql, sid);
		//4.ɾ��
		hql="delete from Survey s where s.id=?";
		surveyDao.batchEntityByHql(hql, sid);
	}
	
	/**
	 * �������
	 * @param sid
	 */
	public void clearSurvey(Integer sid){
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHql(hql, sid);
	}

	/**
	 * ��/�ر� ����
	 * @param sid
	 */
	public void toggleStatus(Integer sid){
		Survey survey=surveyDao.getEntity(sid);
		String hql="update Survey s set s.closed=? where s.id=?";
		surveyDao.batchEntityByHql(hql,!(survey.getClosed()==null?false:survey.getClosed()),sid);
	}
	/**
	 * ����Survey logo��·��
	 * @param sid
	 * @param relativePath
	 */
	public void updateLogoPhotoPath(Integer sid, String relativePath){
		String hql="update Survey s set s.logoPhotoPath=? where s.id=?";
		surveyDao.batchEntityByHql(hql, relativePath,sid);
	}

	/**
	 * ��ѯuser�ĵ��� �����س�page
	 * @param user
	 * @return
	 */
	public List<Survey> getSurveyWithPage(User user){
		String hql="From Survey s where s.user.id=?";
		List<Survey> list=surveyDao.findEntityByHql(hql, user.getId());
		for(Survey s:list){
			//����page
			s.getPages().size();
		}
		return list;
	}
	/**
	 * ��ѯ ���е� ���ŵĵ���
	 * @return
	 */
	public List<Survey> findAllOpenSurvey(){
		String hql="from Survey s where s.closed=?";
		return surveyDao.findEntityByHql(hql, false);
	}
	/**
	 * ��ѯ sid�����µ� ��һ��ҳpage
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid){
		String hql="from Page p where p.survey.id=? order by p.orderno asc";
		List<Page> list=pageDao.findPageByHql(hql, 0, 1, sid);
		Page page=null;
		if(!list.isEmpty()){
			page=list.get(0);
			//����question
			page.getQuestions().size();
		}
		return page;
	}
	
	/**
	 * ��ѯ ������µ���������
	 * @param sid
	 * @return
	 */
	public List<Question> getQuestions(Integer sid){
		String hql="from Question q where q.page.survey.id=?";
		return questionDao.findEntityByHql(hql, sid);
	}
	
	/**
	 * ��ѯ ������µ�����Answer orderby uuid
	 * @param sid
	 * @return
	 */
	public List<Answer> getAnswers(Integer sid){
		String hql="from Answer a where a.surveyId=? order by a.uuid asc";
		return answerDao.findEntityByHql(hql, sid);
	}

}
