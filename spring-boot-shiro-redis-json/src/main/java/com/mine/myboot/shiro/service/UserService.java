package com.mine.myboot.shiro.service;

import java.util.List;

import com.mine.myboot.shiro.pojo.User;

public interface UserService {

	List<User> selectAll();

	User selectUserByUserName(String username);
}
