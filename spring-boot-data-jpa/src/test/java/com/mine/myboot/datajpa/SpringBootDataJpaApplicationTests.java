package com.mine.myboot.datajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.myboot.datajpa.dao.UserRepository;
import com.mine.myboot.datajpa.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDataJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
		User user = userRepository.findById(100);
		System.out.println(user);
	}

}
