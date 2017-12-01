package com.mine.myboot.swagger.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户")
@RestController
@RequestMapping(value = "user")
public class UserController {

	@ApiOperation(value = "添加用户")
	@RequestMapping("add")
	public String add() {
		return "add";
	}

	@ApiOperation(value = "删除用户")
	@RequestMapping("del")
	public String del() {
		return "del";
	}

	@ApiOperation(value = "编辑用户")
	@RequestMapping("edit")
	public String edit() {
		return "edit";
	}

	@ApiOperation(value = "查询用户")
	@RequestMapping("get")
	public String get() {
		return "get";
	}

}
