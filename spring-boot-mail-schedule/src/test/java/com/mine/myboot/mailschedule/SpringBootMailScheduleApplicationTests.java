package com.mine.myboot.mailschedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.myboot.mailschedule.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMailScheduleApplicationTests {

	@Autowired
	private MailService mailService;

	@Test
	public void contextLoads() {

		mailService.sendSimpleMail("xxx@yyy.com", "hello man", "are you ok");
	}

}
