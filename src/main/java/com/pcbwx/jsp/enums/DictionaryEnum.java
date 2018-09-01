package com.pcbwx.jsp.enums;
/**
 * 系统配置枚举类
 * @author 孙贺宇
 * @version 1.0
 *
 */
public enum DictionaryEnum {

	PAY_METHOD("pay_method","付款方式"),
	EXPRESS_STATUS("express_status","运单状态"),
	BUTTON("button","按钮"),
	CARGO_NAME("cargo_name","业务类型（包裹内容）");

	private String code;
	private String descr;
	
	DictionaryEnum(String code, String descr) {
		this.code = code;
		this.descr = descr;
	}

	public String getCode() {
		return code;
	}

	public String getDescr() {
		return descr;
	}

}
