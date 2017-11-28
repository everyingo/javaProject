package test;



import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.mine.model.User;
import com.mine.service.SurveyService;


public class SurveyTest {

	private ApplicationContext ac=null;
	private SurveyService surveyService;
	{
		ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		surveyService=ac.getBean(SurveyService.class);
	}
	
	@Test
	public void testNewSurvey(){
		User user=new User();
		user.setId(2);
		surveyService.newSurvey(user);
	}
}
