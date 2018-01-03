package com.mine.myboot.shiro.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mine.myboot.shiro.pojo.User;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Map<String, Object> map, User user) {

		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			map.put("msg", "用户名或密码不能为空！");
			return "login";
		}

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			return "redirect:index";
		} catch (LockedAccountException lae) {
			token.clear();
			map.put("msg", "用户已经被锁定不能登录，请与管理员联系！");
			return "login";
		} catch (AccountException e) {
			token.clear();
			map.put("msg", e.getMessage());
			return "login";
		} catch (Exception e) {
			token.clear();
			map.put("msg", "500");
			e.printStackTrace();
			return "500";
		}

	}

}
