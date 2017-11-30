package com.mine.myboot.simple.pojo.dto;

import com.mine.myboot.simple.common.base.BaseDTO;
import com.mine.myboot.simple.pojo.User;

public class UserDTO extends BaseDTO<User> {

	private static final long serialVersionUID = 1L;

	@Override
	public User convertTo(String... ignoreName) {
		return null;
	}

	@Override
	public void convertBack(User m, String... ignoreName) {

	}

}
