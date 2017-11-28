package com.mine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Answer;
import com.mine.model.Page;
import com.mine.model.Question;
import com.mine.model.Survey;
import com.mine.service.PageService;
import com.mine.util.DataUtil;

@Service
public class PageServiceImpl extends BaseServiceImpl<Page> implements PageService{

	
	@Autowired
	private BaseDao<Page> pageDao;
	
	@Autowired
	private BaseDao<Answer> answerDao;
	
	@Autowired
	private BaseDao<Question> questionDao;
	/**
	 * ���/���� page
	 * @param model
	 */
    public void saveOrUpdatePage(Page model){
    	pageDao.saveOrUpdateEntity(model);
    }
    /**
     * ɾ��Page ͬʱɾ�� Question,Answer 
     * @param pid
     */
	public void deletePage(Integer pid){
		//1.ɾ��answer
		String hql="delete from Answer a where a.questionId in (select q.id from Question q where q.page.id=?)";
		answerDao.batchEntityByHql(hql, pid);
		//2.ɾ��question
		hql="delete from Question q where q.page.id=?";
		questionDao.batchEntityByHql(hql, pid);
		//3.ɾ��page
		hql="delete from Page p where p.id=?";
		pageDao.batchEntityByHql(hql, pid);
	}
	
	/**
	 * �ƶ�/���� page
	 * @param srcPid
	 * @param targPid
	 * @param pos
	 */
	public void MoveOrCopyPage(Integer srcPid, Integer targPid, Integer pos){
		Page srcPage=pageDao.getEntity(srcPid);
		Survey srcSurvey=srcPage.getSurvey();
		Page targPage=pageDao.getEntity(targPid);
		Survey targSurvey=targPage.getSurvey();
		//���ԭҳ���ڵĵ��� �� Ŀ��ҳ�ĵ�����ͬ ��Ϊ �ƶ�
		if(srcSurvey.getId().equals(targSurvey.getId())){
			srcPage.setOrderno(setOrderno(targPage,pos));
		}
		//���� ��Ϊ����
		else{
			//����page �µ�questions
			srcPage.getQuestions().size();
			//��ȸ���page
			Page newPage=(Page) DataUtil.deepCopy(srcPage);
			newPage.setSurvey(targSurvey);
			pageDao.saveEntity(newPage);
			//����question
			for(Question q:newPage.getQuestions()){
				questionDao.saveEntity(q);
			}
			newPage.setOrderno(setOrderno(targPage,pos));
		}
	}
	//���� orderno
	private Float setOrderno(Page targPage, Integer pos) {
		//֮ǰ
		if(pos==0){
			if(isFirstPage(targPage)){
				return new Float(targPage.getOrderno()-0.01);
			}else{
				Page prePage=getPrePage(targPage);
				return new Float((targPage.getOrderno()+prePage.getOrderno())/2);
			}
		}
		//֮��
		else{
            if(isNextPage(targPage)){
            	return new Float(targPage.getOrderno()+0.01);
			}else{
				Page prePage=getNextPage(targPage);
				return new Float((targPage.getOrderno()+prePage.getOrderno())/2);
			}
		}
		
	}
	//��ѯĿ��page �ĺ�һ��page
	private Page getNextPage(Page targPage) {
		String hql="From Page p where p.survey.id=? and p.orderno>? order by p.orderno asc";
		List<Page> list=pageDao.findPageByHql(hql,0,1,targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	//��ѯĿ��page ��ǰһ��page
	private Page getPrePage(Page targPage) {
		String hql="From Page p where p.survey.id=? and p.orderno<? order by p.orderno desc";
		List<Page> list=pageDao.findPageByHql(hql,0,1,targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	//�ж��Ƿ� Ϊ��ҳ
	private boolean isFirstPage(Page targPage) {
		String hql="select count(p.id) From Page p where p.survey.id=? and p.orderno<?";
		long count=(Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return count==0;
	}
	//�ж��Ƿ� Ϊβҳ
    private boolean isNextPage(Page targPage) {
    	String hql="select count(p.id) From Page p where p.survey.id=? and p.orderno>?";
		long count=(Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return count==0;
	}
    /**
	 * ��ȡǰһҳ
	 * @param pid
	 * @return
	 */
	public Page getPrePage(Integer pid){
		Page page=pageDao.getEntity(pid);
		page=this.getPrePage(page);
		page.getQuestions().size();
		return page;
	}
	/**
	 * ��ȡ��һҳ
	 * @param pid
	 * @return
	 */
	public Page getNextPage(Integer pid){
		Page page=pageDao.getEntity(pid);
		page=this.getNextPage(page);
		page.getQuestions().size();
		return page;
	}
}
