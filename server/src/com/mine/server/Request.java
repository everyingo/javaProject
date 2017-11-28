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
	// ������Դ
	private String url;
	// ����ʽ
	private String method;
	// �������
	private Map<String, List<String>> parameterMapValues;

	// �ڲ�����
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
			CloseUtil.closeIO(is);//�ر�������
		}
		// ��������ͷ
		parseRequestInfo();

	}

	private void parseRequestInfo() {

		if (this.requestInfo == null
				|| (this.requestInfo = this.requestInfo.trim()).equals("")) {
			return;
		}
		// �������
		String paramString = "";
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF))
				.trim();
		int idx = firstLine.indexOf("/"); // /��λ��
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
			} else {// �]�Ѕ���
				this.url = urlStr;
			}
		}
		if (paramString.equals("")) {
			return;
		}
		// ����װ�����
		paramMap(paramString);
	}

	// ����װ�����
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

	// ��ȡ�������
	public String[] getParameterValues(String name) {
		List<String> keyValue = parameterMapValues.get(name);
		if (keyValue == null) {
			return null;
		} else {
			return keyValue.toArray(new String[0]);
		}
	}

	// ��ȡ��������
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
