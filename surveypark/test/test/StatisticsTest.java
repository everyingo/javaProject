package test;

import java.sql.SQLException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mine.service.QuestionStatisticsService;

public class StatisticsTest {

	private ApplicationContext ac=null;
	{
		ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void testStatistic() throws SQLException{
		QuestionStatisticsService qss=(QuestionStatisticsService) ac.getBean("statisticsService");
		qss.getQuestionStatistic(14);
		
	}
}
