package com.mine.action;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Question;
import com.mine.model.statistics.OptionStatisticsModel;
import com.mine.model.statistics.QuestionStatisticsModel;
import com.mine.service.QuestionStatisticsService;

/**
 * 矩阵式问题统计
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class MaxtriStatisticAction extends BaseAction<Question> {


	private static final long serialVersionUID = -6054156757517664926L;

	@Resource(name="statisticsService")
	private QuestionStatisticsService qsService;
	
	private String[] colors={"green","blue","yellow","black","gray"};
	
	private QuestionStatisticsModel qsm;
	
	private Integer qid;
	
	public String[] getColors() {
		return colors;
	}

	public void setColors(String[] colors) {
		this.colors = colors;
	}

	public QuestionStatisticsModel getQsm() {
		return qsm;
	}

	public void setQsm(QuestionStatisticsModel qsm) {
		this.qsm = qsm;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String execute(){
		this.qsm=qsService.getQuestionStatistic(qid);
		Question q=qsm.getQuestion();
		return ""+q.getQuestionType();
	}
	
	/**
	 * 计算每个选项的结果 orida|checkBox
	 * @return
	 */
	public String getValue(Integer rowsIndex,Integer colIndex){
		if(qsm.getCount()>0){
			for(OptionStatisticsModel osm:qsm.getOsms()){
				if(osm.getMatrixRowIndex()==rowsIndex && osm.getMatrixColIndex()==colIndex){
					DecimalFormat df=new DecimalFormat();
					df.applyPattern("#,###.00");
					float scale=(float)osm.getCount()/qsm.getCount()*100;
					return ""+osm.getCount()+"("+df.format(scale)+"%)";
				}
			}
		}
		return "";
	}
	/**
	 * 计算每个选项的结果 select
	 * @return
	 */
	public String getValue(Integer rowsIndex,Integer colIndex,Integer selIndex){
		if(qsm.getCount()>0){
			for(OptionStatisticsModel osm:qsm.getOsms()){
				if(osm.getMatrixRowIndex()==rowsIndex && osm.getMatrixColIndex()==colIndex && osm.getMatrixSelectIndex()==selIndex){
					DecimalFormat df=new DecimalFormat();
					df.applyPattern("#,###.00");
					float scale=(float)osm.getCount()/qsm.getCount()*100;
					return ""+osm.getCount()+"("+df.format(scale)+"%)";
				}
			}
		}
		return "";
	}
	
	/**
	 * 获得像素
	 * @param rowsIndex
	 * @param colIndex
	 * @param selIndex
	 * @return
	 */
	public Integer getPrecent(Integer rowsIndex,Integer colIndex,Integer selIndex){
		if(qsm.getCount()>0){
			for(OptionStatisticsModel osm:qsm.getOsms()){
				if(osm.getMatrixRowIndex()==rowsIndex && osm.getMatrixColIndex()==colIndex && osm.getMatrixSelectIndex()==selIndex){
					DecimalFormat df=new DecimalFormat();
					df.applyPattern("#,###.00");
					float scale=(float)osm.getCount()/qsm.getCount()*100;
					return (int) scale;
				}
			}
		}
		return 0;
	}
	
	

}
