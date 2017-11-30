package com.mine.myboot.redis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.myboot.redis.pojo.Person;
import com.mine.myboot.redis.service.PersonService;
import com.mine.myboot.redis.tools.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class SpringBootRedisApplicationTests {

	/*@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// @Resource(name = "stringRedisTemplate")
	// private ValueOperations<String, String> valOpsStr;

	@Autowired
	private RedisTemplate redisTemplate;

	// @Resource(name = "redisTemplate")
	// private ValueOperations<String, Object> valOps;

	@Autowired
	private PersonService personService;

	@Test
	public void testString() {
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

	}

	@Test
	public void testObjectSet() {
		ValueOperations<String, List<Person>> operations = redisTemplate.opsForValue();
		List<Person> ps = new ArrayList<>();
		ps.add(new Person(1, "z3", 20));
		ps.add(new Person(2, "l4", 20));
		ps.add(new Person(3, "w5", 20));
		operations.set("ps", ps);
	}

	@Test
	public void testObjectGet() {
		ValueOperations<String, List<Person>> valOp = redisTemplate.opsForValue();
		List<Person> ps = valOp.get("ps");
		System.out.println(ps);
	}

	@Test
	public void testHashGet() {
		HashOperations<String, String, String> hashOp = redisTemplate.opsForHash();
		// hashOp.put("hash1", "b", "2");
		String s = hashOp.get("hash1", "b");
		System.out.println(s);
	}

	@Test
	public void testPerson() {
		Person person = personService.getPerson(2);
		System.out.println(person);
	}

	@Test
	public void testPersonList() {
		List<Person> persons = personService.getPersonList();
		System.out.println(persons);
	}*/
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Test
	public void testRedisUtil() {
		redisUtil.set("p1", new Person(1, "z3", 20));
		System.out.println(redisUtil.get("p1"));
	}
	
	

}
