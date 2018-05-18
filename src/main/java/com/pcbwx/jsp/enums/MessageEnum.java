package com.pcbwx.jsp.enums;

/**
 * 角色枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum MessageEnum {

	SUCCESS(0,"成功"),
	INFOMATION(1, "信息"),
	WARNING(2,"警告/错误"),
	MESSAGE_SENDED(4, "消息已发送过"),
	FAIL(6,"发送失败");
	
	private int code;
	private String descr;
	
	private MessageEnum(int code, String descr) {
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
