package com.mine.myboot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/add")
	public String add() {
		/* int i = 10 / 0; */
		return "add";
	}

	@RequestMapping("/list")
	public String list() {
		return "list";
	}

	@RequestMapping("/edit")
	public String edit() {
		return "edit";
	}

	@RequestMapping("/del")
	public String del() {
		return "del";
	}
}
