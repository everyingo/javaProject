package com.mine.myboot.redis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.mine.myboot.redis.pojo.Person;
import com.mine.myboot.redis.service.PersonService;

@SuppressWarnings("all")
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public Person getPerson(Integer id) {
		String key = "person_";
		ValueOperations<String, Person> operations = redisTemplate.opsForValue();
		if (redisTemplate.hasKey(key + id)) {
			return operations.get(key + id);
		}
		Person person = new Person(id, "kd", 32);
		operations.set(key + id, person, 10, TimeUnit.MINUTES);
		return person;
	}

	@Override
	public List<Person> getPersonList() {
		HashOperations<String, Integer, Person> operations = redisTemplate.opsForHash();
		if (redisTemplate.hasKey("person_list")) {
			Map<Integer, Person> personMap = operations.entries("person_list");
			List<Person> persons = new ArrayList<>();
			for (Integer key : personMap.keySet()) {
				persons.add(personMap.get(key));
			}
			return persons;
		}
		return setPersonList();
	}

	private List<Person> setPersonList() {
		HashOperations<String, String, Person> operations = redisTemplate.opsForHash();
		Map<String, Person> personMap = new HashMap<>();
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Person person = new Person(i, "name-" + i, (i + 1));
			operations.put("person_list",String.valueOf(i), person);
			personMap.put(String.valueOf(i), person);
			persons.add(person);
		}
		
		return persons;
	}

}
