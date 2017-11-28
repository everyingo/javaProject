package com.mine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.dao.BaseDao;
import com.mine.model.Answer;
import com.mine.model.Question;
import com.mine.model.statistics.OptionStatisticsModel;
import com.mine.model.statistics.QuestionStatisticsModel;
import com.mine.service.QuestionStatisticsService;

@Service("statisticsService")
public class QuestionStatisticsServiceImpl implements QuestionStatisticsService {

	@Autowired
	private BaseDao<Question> questionDao;
	
	@Autowired
	private BaseDao<Answer>  answerDao;
	/**
	 * �������ͳ��ģ��
	 */
	public QuestionStatisticsModel getQuestionStatistic(Integer qid) {
		QuestionStatisticsModel qsm=new QuestionStatisticsModel();
		//����question
		Question q=questionDao.getEntity(qid);
		qsm.setQuestion(q);
		//����count���ش�������������
		String qhql="select count(*) from Answer a where a.questionId=?";
		Long qcount=(Long) answerDao.uniqueResult(qhql, qid);
		qsm.setCount(qcount);
		//���� ѡ��ģ��
		int qt=q.getQuestionType();
		String ohql=null;
		switch (qt) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			
			OptionStatisticsModel osm=null;
			String[] optionArr=q.getOptionArr();
			for(int i=0;i<optionArr.length;i++){
				osm=new OptionStatisticsModel();
				osm.setOptionIndex(i);//����ѡ�������
				osm.setOptionLabel(optionArr[i]);//����ѡ��ı�ǩ
				ohql="select count(*) from Answer a where a.questionId=? and concat(',',a.answerIds,',') like ?";
				Long ocount=(Long) answerDao.uniqueResult(ohql, qid,"%,"+i+",%");
				osm.setCount(ocount.intValue());
				
				qsm.getOsms().add(osm);
			}
			//other
			if(q.getOther()){
				osm=new OptionStatisticsModel();
				osm.setOptionLabel("����");//����ѡ��ı�ǩ
				ohql="select count(*) from Answer a where a.questionId=? and concat(',',a.answerIds,',') like ?";
				Long ocount=(Long) answerDao.uniqueResult(ohql, qid,"%,other,%");
				osm.setCount(ocount.intValue());
				qsm.getOsms().add(osm);
			}
			break;
		case 6:
		case 7:
		case 8:
			String[] rows=q.getMatrixRowTitleArr();
			String[] cols=q.getMatriColTitleArr();
			String[] options=q.getMatriSelectOptionArr();
			for(int i=0;i<rows.length;i++){
				for (int j = 0; j < cols.length; j++) {
					if(qt!=8){
						osm=new OptionStatisticsModel();
						osm.setMatrixRowIndex(i);
						osm.setMatrixRowLabel(rows[i]);
						osm.setMatrixColIndex(j);
						osm.setMatrixColLabel(cols[j]);
						
						ohql="select count(*) from Answer a where a.questionId=? and concat(',',a.answerIds,',') like ?";
						Long ocount=(Long) answerDao.uniqueResult(ohql, qid,"%,"+i+"_"+j+",%");
						osm.setCount(ocount.intValue());
						qsm.getOsms().add(osm);
						
					}else{
						for (int k = 0; k < options.length; k++) {
							osm=new OptionStatisticsModel();
							osm.setMatrixRowIndex(i);
							osm.setMatrixRowLabel(rows[i]);
							osm.setMatrixColIndex(j);
							osm.setMatrixColLabel(cols[j]);
							osm.setMatrixSelectIndex(k);
							osm.setMatrixSelectLabel(options[k]);
							
							ohql="select count(*) from Answer a where a.questionId=? and concat(',',a.answerIds,',') like ?";
							Long ocount=(Long) answerDao.uniqueResult(ohql, qid,"%,"+i+"_"+j+"_"+k+",%");
							osm.setCount(ocount.intValue());
							qsm.getOsms().add(osm);
						}
					}
				}
			}
			break;
		}
		return qsm;
	}

}
