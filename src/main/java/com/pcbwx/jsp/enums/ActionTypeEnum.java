/**
 * 
 */
package com.pcbwx.jsp.enums;

/**
 * 行为类型枚举类
 * 
 * @author 王海龙
 *
 */
public enum ActionTypeEnum {
	//--------------------推送接口-------------------------
	ROUTE_PUSH("route.push", "路由推送"),
	ORDER_FILTER_PUSH("order.filter.push", "订单筛选推送"),
	
	//--------------------用户接口-------------------------
	ROLE("role", "获取角色列表"),
	USERS("users", "获取用户列表"),
	USERS_ADD("user.add", "新增用户"),
	USERS_DELETE("user.delete", "删除用户"),
	USER_ENABLE("user.enable", "启用禁用"),
	USER_ROLE("user.role", "配置用户角色"),
	USER_PASSWORD("user.password", "重置密码"),
	
	//--------------------下单接口--------------------------
	EXPRESS_INFO("express.info", "解析二维码"),
	EXPRESS_ORDER("express.order", "下单"),
	
	//--------------------系统配置接口----------------------
	SYSTEM_ADDRESS("system.address", "行政区列表"),
	ADDRESS_EXPORT("address.export", "行政区列表导出"),
	SYSTEM_COMPANY("system.company", "快递公司列表"),
	SYSTEM_PRODUCT("system.product", "业务类型列表"),
	PRODUCT_ADD("product.add", "新增业务类型"),
	PRODUCT_ENABLE("product.enable", "启用/禁用业务类型"),
	SYSTEM_SERVICE("system.service", "增值服务列表"),
	SERVICE_ENABLE("service.enable", "启用/禁用增值服务"),
	
	//--------------------权限接口-------------------------
	AUTH_PASSWORD("auth.password", "修改密码"),
	
	//--------------------通讯录接口-----------------------
	CONTACT_RECIPIENT("contact.recipient", "收件人信息列表"),
	RECIPIENT_EXPORT("recipient.export", "收件人信息导出"),
	CONTACT_SENDER("contact.sender", "寄件人信息列表"),
	SENDER_EXPORT("sender.export", "寄件人信息导出"),
	SENDER_ADDRESS("sender.address", "寄件人地址列表"),
	SENDER_ADDRESS_EXPORT("sender.address.export", "寄件人地址导出"),
	
	//--------------------快递单接口-----------------------
	ORDER_DETAIL("order.detail", "获取运单详情"),
	ORDER_EXPRESS("order.express", "获取快递单列表"),
	ORDER_EXPRESS_EXPORT("order.express.export", "导出快递单");

	private String code; // 日志代码
	private String desc; // 日志描述

	private ActionTypeEnum(String code, String desc) {
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
