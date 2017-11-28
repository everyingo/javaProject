package com.mine.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.mine.util.CloseUtil;

public class Request {

	public static String CRLF = "\r\n";
	public static String BLANK = " ";
	// 请求资源
	private String url;
	// 请求方式
	private String method;
	// 请求参数
	private Map<String, List<String>> parameterMapValues;

	// 内部参数
	private InputStream is;
	private String requestInfo;

	public Request() {
		url = "";
		method = "";
		parameterMapValues = new HashMap<String, List<String>>();
	}

	public Request(InputStream is) {
		this();
		this.is = is;
		byte[] data = new byte[20480];
		try {
			int len = is.read(data);
			
			requestInfo = new String(data, 0, len);
			System.out.println(requestInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			CloseUtil.closeIO(is);//关闭输入流
		}
		// 解析请求头
		parseRequestInfo();

	}

	private void parseRequestInfo() {

		if (this.requestInfo == null
				|| (this.requestInfo = this.requestInfo.trim()).equals("")) {
			return;
		}
		// 请求参数
		String paramString = "";
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF))
				.trim();
		int idx = firstLine.indexOf("/"); // /的位置
		this.method = firstLine.substring(0, idx).trim();
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/"))
				.trim();
		if (method.equalsIgnoreCase("POST")) {
			this.url = urlStr;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF))
					.trim();
		} else if (method.equalsIgnoreCase("GET")) {
			if (urlStr.contains("?")) {
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];
				paramString = urlArray[1];
			} else {// 沒有參數
				this.url = urlStr;
			}
		}
		if (paramString.equals("")) {
			return;
		}
		// 解析装配参数
		paramMap(paramString);
	}

	// 解析装配参数
	private void paramMap(String paramString) {
		StringTokenizer token = new StringTokenizer(paramString, "&");
		while (token.hasMoreTokens()) {
			String keyValue = token.nextToken();
			String[] keyValues = keyValue.split("=");
			if (keyValues.length == 1) {
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}
			String key = keyValues[0].trim();
			String value = null == keyValues[1] ? null : decode(keyValues[1].trim(), "utf-8");
			if (!parameterMapValues.containsKey(key)) {
				parameterMapValues.put(key, new ArrayList<String>());
			}
			List<String> values = parameterMapValues.get(key);
			values.add(value);
		}

	}

	// 获取多个参数
	public String[] getParameterValues(String name) {
		List<String> keyValue = parameterMapValues.get(name);
		if (keyValue == null) {
			return null;
		} else {
			return keyValue.toArray(new String[0]);
		}
	}

	// 获取单个参数
	public String getParameter(String name) {
		List<String> keyValue = parameterMapValues.get(name);
		if (keyValue == null) {
			return null;
		} else {
			return keyValue.get(0);
		}
	}

	public String decode(String str,String code) {
		try {
			return java.net.URLDecoder.decode(str, code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
