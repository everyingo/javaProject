package com.mine.core.common.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * setCookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @param maxAge
	 * @param path
	 * @param domain
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge,
			String path, String domain) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		// -1 浏览器关闭失效 0 立即失效 >0指定时间失效
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		cookie.setDomain(domain);
		response.addCookie(cookie);
	}

	/**
	 * getCookie
	 * 
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (ValidateUtil.isValidate(cookies)) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * clearCookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param path
	 * @param domian
	 */
	public static void clearCookie(HttpServletResponse response, String cookieName, String path, String domian) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(path);
		cookie.setDomain(domian);
		response.addCookie(cookie);
	}
}
