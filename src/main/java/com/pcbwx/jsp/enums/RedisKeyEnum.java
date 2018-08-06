/**
 * 
 */
package com.pcbwx.jsp.enums;

/**
 * redis-key值枚举类
 * 
 * @author 孙贺宇
 *
 */
public enum RedisKeyEnum {
	
	DICTIONARY("dictionary", "字典表"),
	USERROLE("user_role", "角色表"),
	USERROLERELATION("user_role_relation", "用户角色表"),
	USERAUTH("user_auth", "权限表"),
	ROLEAUTH("role_auth", "角色权限表"),
	MENU("menu", "菜单表");

	private String code; // 日志代码
	private String desc; // 日志描述

	private RedisKeyEnum(String code, String desc) {
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
