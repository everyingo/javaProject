package com.mine.myboot.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.mine.myboot.simple.common.base.BaseController;
import com.mine.myboot.simple.common.share.ResultData;
import com.mine.myboot.simple.pojo.User;
import com.mine.myboot.simple.service.UserService;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {


	@Autowired
	private UserService userService;

	@RequestMapping("list")
	public ResultData list(Integer pageNo, Integer pageSize) {
		PageInfo<User> page = userService.selectPage(pageNo, pageSize);
		rd.put("page", page);
		return rd;
	}
}
