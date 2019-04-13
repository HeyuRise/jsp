/**
 * 
 */
package com.heyu.jsp.enums;

/**
 * redis-key值枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum RedisKeyEnum {
	
	DICTIONARY("dictionary", "字典表");

	private String code;
	private String desc;

	RedisKeyEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
