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
	 * 添加/更新 page
	 * @param model
	 */
    public void saveOrUpdatePage(Page model){
    	pageDao.saveOrUpdateEntity(model);
    }
    /**
     * 删除Page 同时删除 Question,Answer 
     * @param pid
     */
	public void deletePage(Integer pid){
		//1.删除answer
		String hql="delete from Answer a where a.questionId in (select q.id from Question q where q.page.id=?)";
		answerDao.batchEntityByHql(hql, pid);
		//2.删除question
		hql="delete from Question q where q.page.id=?";
		questionDao.batchEntityByHql(hql, pid);
		//3.删除page
		hql="delete from Page p where p.id=?";
		pageDao.batchEntityByHql(hql, pid);
	}
	
	/**
	 * 移动/复制 page
	 * @param srcPid
	 * @param targPid
	 * @param pos
	 */
	public void MoveOrCopyPage(Integer srcPid, Integer targPid, Integer pos){
		Page srcPage=pageDao.getEntity(srcPid);
		Survey srcSurvey=srcPage.getSurvey();
		Page targPage=pageDao.getEntity(targPid);
		Survey targSurvey=targPage.getSurvey();
		//如果原页所在的调查 与 目标页的调查相同 则为 移动
		if(srcSurvey.getId().equals(targSurvey.getId())){
			srcPage.setOrderno(setOrderno(targPage,pos));
		}
		//否则 则为复制
		else{
			//加载page 下的questions
			srcPage.getQuestions().size();
			//深度复制page
			Page newPage=(Page) DataUtil.deepCopy(srcPage);
			newPage.setSurvey(targSurvey);
			pageDao.saveEntity(newPage);
			//保存question
			for(Question q:newPage.getQuestions()){
				questionDao.saveEntity(q);
			}
			newPage.setOrderno(setOrderno(targPage,pos));
		}
	}
	//设置 orderno
	private Float setOrderno(Page targPage, Integer pos) {
		//之前
		if(pos==0){
			if(isFirstPage(targPage)){
				return new Float(targPage.getOrderno()-0.01);
			}else{
				Page prePage=getPrePage(targPage);
				return new Float((targPage.getOrderno()+prePage.getOrderno())/2);
			}
		}
		//之后
		else{
            if(isNextPage(targPage)){
            	return new Float(targPage.getOrderno()+0.01);
			}else{
				Page prePage=getNextPage(targPage);
				return new Float((targPage.getOrderno()+prePage.getOrderno())/2);
			}
		}
		
	}
	//查询目标page 的后一个page
	private Page getNextPage(Page targPage) {
		String hql="From Page p where p.survey.id=? and p.orderno>? order by p.orderno asc";
		List<Page> list=pageDao.findPageByHql(hql,0,1,targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	//查询目标page 的前一个page
	private Page getPrePage(Page targPage) {
		String hql="From Page p where p.survey.id=? and p.orderno<? order by p.orderno desc";
		List<Page> list=pageDao.findPageByHql(hql,0,1,targPage.getSurvey().getId(),targPage.getOrderno());
		return list.get(0);
	}
	//判断是否 为首页
	private boolean isFirstPage(Page targPage) {
		String hql="select count(p.id) From Page p where p.survey.id=? and p.orderno<?";
		long count=(Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return count==0;
	}
	//判断是否 为尾页
    private boolean isNextPage(Page targPage) {
    	String hql="select count(p.id) From Page p where p.survey.id=? and p.orderno>?";
		long count=(Long) pageDao.uniqueResult(hql, targPage.getSurvey().getId(),targPage.getOrderno());
		return count==0;
	}
    /**
	 * 获取前一页
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
	 * 获取后一页
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
