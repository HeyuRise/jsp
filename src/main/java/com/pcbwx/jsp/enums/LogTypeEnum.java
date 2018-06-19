/**
 * 
 */
package com.pcbwx.jsp.enums;

/**
 * 日志类型枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum LogTypeEnum {
	LOG_SYSTEM("SYSTEM", "系统日志"), 
	LOG_OPERATE("OPERATE", "操作日志"), 
	LOG_ERROR("ERROR", "错误日志"), 
	LOG_NORMAL("NORMAL", "普通日志"), 
	LOG_WARN("WARN", "警告日志");

	private String code; // 日志代码
	private String desc; // 日志描述

	private LogTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
