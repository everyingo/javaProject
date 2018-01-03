package com.mine.myboot.shiro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping("/add")
	public Map<String, Object> add() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		resultMap.put("data", "add");
		return resultMap;
	}

	@RequestMapping("/list")
	public Map<String, Object> list() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		resultMap.put("data", "list");
		return resultMap;
	}

	@RequestMapping("/edit")
	public Map<String, Object> edit() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		resultMap.put("data", "edit");
		return resultMap;
	}

	@RequestMapping("/del")
	public Map<String, Object> del() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		resultMap.put("data", "del");
		return resultMap;
	}
}
