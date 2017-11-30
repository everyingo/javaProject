package com.mine.myboot.redis.service;

import java.util.List;

import com.mine.myboot.redis.pojo.Person;

public interface PersonService {

	Person getPerson(Integer id);

	List<Person> getPersonList();
}
