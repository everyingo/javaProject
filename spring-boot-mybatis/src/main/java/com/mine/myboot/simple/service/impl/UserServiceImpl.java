package com.mine.myboot.simple.service.impl;

import org.springframework.stereotype.Service;

import com.mine.myboot.simple.common.base.BaseServiceImpl;
import com.mine.myboot.simple.pojo.User;
import com.mine.myboot.simple.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

}
