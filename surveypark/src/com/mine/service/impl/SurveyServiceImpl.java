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
	 *  查询我的调查
	 */
	public List<Survey> getMySurveys(User user) {
		String hql="From Survey s where s.user.id=?";
		return surveyDao.findEntityByHql(hql, user.getId());
	}

	/**
	 * 新建调查
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
	 * 通过主键查询Survey
	 */
	public Survey getSurveyById(Serializable id) {
		
		return surveyDao.getEntity(id);
	}

	/**
	 * 通过主键查询Survey 和关联的子类
	 */
	public Survey getSurveyAndChildren(Serializable sid) {
		Survey survey=this.getSurveyById(sid);
		for (Page page : survey.getPages()) {
			 page.getQuestions().isEmpty();
		}
		return survey;
	}

	/**
	 * 更新Survey
	 */
	public void updateSurvey(Survey model) {
		surveyDao.updateEntity(model);
	}

	/**
	 *  删除调查 Survey 同时删除 page,question,answer
	 */
	public void deleteSurvey(Integer sid) {
        //1.删除 answer
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHql(hql, sid);
		//2.删除question
		hql="delete from Question q where q.page.id in (select p.id from Page p where p.survey.id=?)";
		questionDao.batchEntityByHql(hql, sid);
		//3.删除page
		hql="delete from Page p where p.survey.id=?";
		pageDao.batchEntityByHql(hql, sid);
		//4.删除
		hql="delete from Survey s where s.id=?";
		surveyDao.batchEntityByHql(hql, sid);
	}
	
	/**
	 * 清除调查
	 * @param sid
	 */
	public void clearSurvey(Integer sid){
		String hql="delete from Answer a where a.surveyId=?";
		answerDao.batchEntityByHql(hql, sid);
	}

	/**
	 * 打开/关闭 调查
	 * @param sid
	 */
	public void toggleStatus(Integer sid){
		Survey survey=surveyDao.getEntity(sid);
		String hql="update Survey s set s.closed=? where s.id=?";
		surveyDao.batchEntityByHql(hql,!(survey.getClosed()==null?false:survey.getClosed()),sid);
	}
	/**
	 * 更新Survey logo的路径
	 * @param sid
	 * @param relativePath
	 */
	public void updateLogoPhotoPath(Integer sid, String relativePath){
		String hql="update Survey s set s.logoPhotoPath=? where s.id=?";
		surveyDao.batchEntityByHql(hql, relativePath,sid);
	}

	/**
	 * 查询user的调查 并加载出page
	 * @param user
	 * @return
	 */
	public List<Survey> getSurveyWithPage(User user){
		String hql="From Survey s where s.user.id=?";
		List<Survey> list=surveyDao.findEntityByHql(hql, user.getId());
		for(Survey s:list){
			//加载page
			s.getPages().size();
		}
		return list;
	}
	/**
	 * 查询 所有的 开放的调查
	 * @return
	 */
	public List<Survey> findAllOpenSurvey(){
		String hql="from Survey s where s.closed=?";
		return surveyDao.findEntityByHql(hql, false);
	}
	/**
	 * 查询 sid调查下的 第一个页page
	 * @param sid
	 * @return
	 */
	public Page getFirstPage(Integer sid){
		String hql="from Page p where p.survey.id=? order by p.orderno asc";
		List<Page> list=pageDao.findPageByHql(hql, 0, 1, sid);
		Page page=null;
		if(!list.isEmpty()){
			page=list.get(0);
			//加载question
			page.getQuestions().size();
		}
		return page;
	}
	
	/**
	 * 查询 调查底下的所有问题
	 * @param sid
	 * @return
	 */
	public List<Question> getQuestions(Integer sid){
		String hql="from Question q where q.page.survey.id=?";
		return questionDao.findEntityByHql(hql, sid);
	}
	
	/**
	 * 查询 调查底下的所有Answer orderby uuid
	 * @param sid
	 * @return
	 */
	public List<Answer> getAnswers(Integer sid){
		String hql="from Answer a where a.surveyId=? order by a.uuid asc";
		return answerDao.findEntityByHql(hql, sid);
	}

}
