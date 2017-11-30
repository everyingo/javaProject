package com.mine.myboot.simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mine.myboot.simple.common.properties.UserProperties;

@RestController
public class IndexController {

	@Autowired
	private UserProperties userProperties;
	@RequestMapping("index")
	public String index(){
		return "hello spring boot!!! he"+userProperties.getUname()+":"+userProperties.getAge();
	}
}
