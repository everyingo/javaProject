package com.mine.myboot.simple.common.share;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据实体 summer
 */
public class ResultData implements Serializable {

	private static final long serialVersionUID = -1022626659766618145L;

	public enum CodeType {
		success, error, nologin
	}

	// 状态码
	private int code;

	// 信息
	private String msg;

	// 枚举状态(用于设置code)
	@SuppressWarnings("unused")
	private CodeType codeType;

	// 返回数据
	private Map<String, Object> data;

	public static ResultData newInstance() {
		return new ResultData();
	}

	private ResultData() {
		this.setCodeType(CodeType.success);
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setCodeType(CodeType codeType, String msg) {
		this.setCodeType(codeType);
		this.msg = msg;
	}

	public void setCodeType(CodeType codeType) {
		switch (codeType) {
		case success:
			this.code = 1;
			this.msg = "success";
			break;
		case error:
			this.code = 0;
			this.msg = "error";
			break;
		case nologin:
			this.code = -1;
			this.msg = "nologin";
			break;

		default:
			break;
		}
	}

	public void put(String key, Object value) {
		if (this.data == null)
			this.data = new HashMap<String, Object>();
		this.data.put(key, value);
	}

}
