package com.mine.myboot.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mine.myboot.shiro.auth.ShiroService;

@Controller
public class SystemController {

	@Autowired
	private ShiroService shiroService;

	/**
	 * 重新加载权限
	 * 
	 * @return
	 */
	@RequestMapping("updatePermission")
	public String updatePermission() {
		shiroService.updatePermission();
		return "index";
	}
}
