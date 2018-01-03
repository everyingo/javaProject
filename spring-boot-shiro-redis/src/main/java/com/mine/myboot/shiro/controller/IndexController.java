package com.mine.myboot.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({ "/", "index" })
	public String index() {

		return "index";
	}

	@RequestMapping("/403")
	public String error403() {

		return "error/403";
	}

	@RequestMapping("/login")
	public String login() {

		return "login";
	}
}
