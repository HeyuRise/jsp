package com.pcbwx.ebes.enums;

public enum ClickEnum {
	PRINT(2,"打印"),
	SERVICE_ENABLE(12,"增值服务启用/禁用"),
	USER_ENABLE(8, "用户启用/禁用"),
	PRODUCT_ENABLE(11, "业务类型启用/禁用");
	
	private int code;
	private String descr;
	
	private ClickEnum(int code, String descr) {
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
