package com.pcbwx.jsp.enums;
/**
 * 系统配置枚举类
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum ConfigEnum {
	
	//------------------ 配置文件配置项 -------------------------------
	SERVICE_MAIN_URL("service.main.url", "服务地址前缀"),
	DEBUG_LOG("debug.log", "日志环境"),
	DEBUG_WITHOUT_LOGIN("debug.without.login", "是否调试(免登录)"),
	
	//------------------ config表配置项 -------------------------------

	//------------------ record_utils表记录项 -------------------------
	LAST_RELOAD_TIME("last_reload_time", "上次缓存载入时间");
	
	
	private String code;
	private String descr;
	
	ConfigEnum(String code, String descr) {
		this.code = code;
		this.descr = descr;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
