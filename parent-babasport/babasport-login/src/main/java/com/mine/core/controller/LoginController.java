package com.mine.core.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.JSONObject;
import com.mine.core.bean.user.Buyer;
import com.mine.core.common.tools.JsonUtil;
import com.mine.core.common.tools.PasswordUtil;
import com.mine.core.common.tools.RequestUtil;
import com.mine.core.common.tools.ValidateUtil;
import com.mine.core.common.web.Constants;
import com.mine.core.service.user.BuyerService;
import com.mine.core.service.user.SessionProvider;

@Controller
public class LoginController {

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private SessionProvider sessionProvider;

	/**
	 * to login
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.aspx", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * login
	 * 
	 * @param data
	 * @param username
	 * @param password
	 * @param returnUrl
	 * @return
	 */
	@RequestMapping(value = "/login.aspx", method = RequestMethod.POST)
	public String login(Map<String, Object> data, HttpServletRequest request, HttpServletResponse response,
			String username, String password, String returnUrl) {
		if (!ValidateUtil.isValidate(username)) {
			data.put("error", "用户名称不能为空");
			return "login";
		}
		if (!ValidateUtil.isValidate(password)) {
			data.put("error", "用户密码不能为空");
			return "login";
		}
		Buyer buyer = buyerService.selectBuyerByUsername(username);
		if (buyer == null) {
			data.put("error", "用户名称不存在");
			return "login";
		}
		if (!cheakPassword(buyer.getPassword(), password)) {
			data.put("error", "用户密码错误");
			return "login";
		}
		if (!ValidateUtil.isValidate(returnUrl)) {
			// 首页
			returnUrl = Constants.BABASPORT_INDEX_URL;
		}
		// 保存用户名到Session中(Redis中)
		sessionProvider.setAttribuerForUsername(RequestUtil.getCSESSIONID(request, response), username);
		return "redirect:" + returnUrl;
	}

	/**
	 * isLogin 跨域访问
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/isLogin.aspx")
	@ResponseBody
	public MappingJacksonValue isLogin(String callback, HttpServletRequest request, HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		Integer result = 0;
		String value = sessionProvider.getAttributeForUsername(RequestUtil.getCSESSIONID(request, response));
		if (value != null) {
			result = 1;
			jo.put("username", value);
		}
		jo.put("result", result);
		MappingJacksonValue mjv = new MappingJacksonValue(JsonUtil.object2Jsonstr(jo));
		mjv.setJsonpFunction(callback);
		return mjv;
	}

	/**
	 * cheakPassword
	 * 
	 * @param spwd
	 * @param lpwd
	 * @return
	 */
	private boolean cheakPassword(String spwd, String lpwd) {
		return PasswordUtil.encodePassword(lpwd).equals(spwd);
	}
}
