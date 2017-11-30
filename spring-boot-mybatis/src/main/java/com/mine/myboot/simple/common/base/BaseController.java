package com.mine.myboot.simple.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.mine.myboot.simple.common.share.ResultData;



public class BaseController {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected ResultData rd;

	@ModelAttribute
	public void setParam(HttpServletRequest request, HttpServletResponse response) {
		this.rd = ResultData.newInstance();
		this.request = request;
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
