package com.pcbwx.jsp.enums;

public enum ExpressStatusEnum {
	
	ERROR("error","不可收派件"),
	WAIT_PUSH("wait.push","等待筛选结果"),
	WAIT_PICK_UP("wait.pick.up","等待顺丰收件"),
	DELEIVER("deleiver","配送中"),
	RECEIVED("received","已签收");
	
	private String code;
	private String descr;
	
	private ExpressStatusEnum(String code, String descr){
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
