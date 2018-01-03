package com.mine.myboot.shiro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.mine.myboot.shiro.dao.UserDao;
import com.mine.myboot.shiro.pojo.User;
import com.mine.myboot.shiro.pojo.UserQuery;
import com.mine.myboot.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> selectAll() {

		return userDao.selectByExample(null);
	}

	@Override
	public User selectUserByUserName(String username) {
		UserQuery userQuery = new UserQuery();
		if (!StringUtils.isEmpty(username)) {
			userQuery.createCriteria().andUsernameEqualTo(username);
			List<User> userList = userDao.selectByExample(userQuery);
			if (userList != null && userList.size() > 0) {
				return userList.get(0);
			}
		}
		return null;
	}

}
