package test;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mine.model.User;
import com.mine.service.UserService;

public class UserTest {

	private ApplicationContext ac=null;
	private UserService userService;
	{
		ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		userService=ac.getBean(UserService.class);
	}
	
	@Test
	public void testSave() throws SQLException{
		User u=new User();
		u.setEmail("xx@163.com");
		u.setNickName("haha");
		u.setUsername("admin");
		u.setPassword("123456");
		
		userService.saveEntity(u);
	}
	@Test
	public void testGet(){
		User u=userService.getEntity(1);
		System.out.println(u);
	}
}
