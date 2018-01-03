package com.mine.myboot.shiro.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mine.myboot.shiro.pojo.User;

@RestController
public class LoginController {

	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request, User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			resultMap.put("msg", "用户名或密码不能为空！");
			resultMap.put("status", "fail");
			return resultMap;
		}

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			resultMap.put("status", "success");
		} catch (LockedAccountException lae) {
			token.clear();
			resultMap.put("msg", "用户已经被锁定不能登录，请与管理员联系！");
			resultMap.put("status", "fail");
		} catch (AccountException e) {
			token.clear();
			resultMap.put("msg", e.getMessage());
			resultMap.put("status", "fail");
		} catch (Exception e) {
			token.clear();
			resultMap.put("msg", "500");
			resultMap.put("status", "fail");
			e.printStackTrace();
		}
		return resultMap;

	}

	@RequestMapping(value = "/logOut")
	public Map<String, Object> logout() {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			// 退出
			SecurityUtils.getSubject().logout();
			resultMap.put("status", "success");
			resultMap.put("msg", "登出成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			resultMap.put("status", "fail");
			resultMap.put("msg", "登出失败");
		}
		return resultMap;
	}

	@RequestMapping(value = "/unauth")
	public Map<String, Object> unauth() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "nologin");
		resultMap.put("msg", "未登录");
		return resultMap;
	}

	@RequestMapping(value = "/403")
	public Map<String, Object> noPermission() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "noPermission");
		resultMap.put("msg", "没有访问权限");
		return resultMap;
	}

}
