package com.mine.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/control")
public class CenterController {

	@RequestMapping(value = "/index.do")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/top.do")
	public String top() {
		return "top";
	}

	@RequestMapping(value = "/main.do")
	public String main() {
		return "main";
	}

	@RequestMapping(value = "/left.do")
	public String left() {
		return "left";
	}

	@RequestMapping(value = "/right.do")
	public String right() {
		return "right";
	}

	@RequestMapping(value = "/frame/product_main.do")
	public String product_main() {
		return "frame/product_main";
	}

	@RequestMapping(value = "/frame/product_left.do")
	public String product_left() {
		return "frame/product_left";
	}
}
