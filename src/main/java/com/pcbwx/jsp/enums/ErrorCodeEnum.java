package com.pcbwx.jsp.enums;

/**
 * 角色枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum ErrorCodeEnum {

	SYSTEM_ERROR(-1, "系统错误"),
	SUCCESS(0, "请求成功"),
	ORDER_FILTER(1, "人工筛单"),
	TOKEN_TIMEOUT(100, "token过期"),
	ILLEGAL_ACTION(20000, "非法action"),
	ILLEGAL_PARAM(20001, "非法参数"),
	ILLEGAL_USER(20002, "非法用户"),
	NOT_FOUND(20004, "记录不存在");
	
	private int code;
	private String descr;
	
	private ErrorCodeEnum(int code, String descr) {
		this.code = code;
		this.descr = descr;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

}
