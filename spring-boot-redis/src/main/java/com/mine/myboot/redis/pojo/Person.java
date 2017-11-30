package com.mine.myboot.redis.pojo;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 3344215125420527314L;

	private Integer id;

	private String username;

	private Integer age;

	public Person() {
		super();
	}

	public Person(Integer id, String username, Integer age) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", age=" + age + "]";
	}

}
