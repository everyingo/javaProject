package com.mine.core.bean;

import java.io.Serializable;
import java.util.Date;

public class TestTb implements Serializable {

	private static final long serialVersionUID = -6272239072872650883L;

	private Integer id;

	private String name;

	private Date birthday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
