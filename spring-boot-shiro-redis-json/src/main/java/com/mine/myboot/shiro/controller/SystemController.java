package com.mine.myboot.shiro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mine.myboot.shiro.auth.ShiroService;

@RestController
public class SystemController {

	@Autowired
	private ShiroService shiroService;

	/**
	 * 重新加载权限
	 * 
	 * @return
	 */
	@RequestMapping("updatePermission")
	public Map<String, Object> updatePermission() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		shiroService.updatePermission();
		resultMap.put("status", "success");
		return resultMap;
	}
}
