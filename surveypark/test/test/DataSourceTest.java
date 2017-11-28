package test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mine.action.MaxtriStatisticAction;
import com.mine.service.QuestionStatisticsService;

public class DataSourceTest {

	private ApplicationContext ac=null;
	{
		ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void testGetConnection() throws SQLException{
		QuestionStatisticsService ds=(QuestionStatisticsService) ac.getBean("statisticsService");
		System.out.println(ds.getClass().getName());
	}
}
