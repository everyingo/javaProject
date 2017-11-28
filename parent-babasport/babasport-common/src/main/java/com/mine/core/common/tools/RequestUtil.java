package com.mine.core.common.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mine.core.common.web.Constants;

public class RequestUtil {

	// 获取
	public static String getCSESSIONID(HttpServletRequest request, HttpServletResponse response) {

		String value = CookieUtil.getCookie(request, Constants.CSESSIONID);
		if (value != null) {
			return value;
		}
		String csessionid = StringUtil.getUUID();
		CookieUtil.setCookie(response, Constants.CSESSIONID, csessionid, -1, "/", "*.abc.com");
		return csessionid;
	}
}
