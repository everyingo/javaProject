package com.mine.myboot.swagger.controller.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "首页")
@RestController
@RequestMapping(value = "index")
public class IndexController {

	@ApiOperation(value = "添加首页")
	@RequestMapping("add")
	public String add() {
		return "add";
	}

	@ApiOperation(value = "删除首页")
	@RequestMapping("del")
	public String del() {
		return "del";
	}

	@ApiOperation(value = "编辑首页")
	@RequestMapping("edit")
	public String edit() {
		return "edit";
	}

	@ApiOperation(value = "查询首页")
	@RequestMapping("get")
	public String get() {
		return "get";
	}
}
