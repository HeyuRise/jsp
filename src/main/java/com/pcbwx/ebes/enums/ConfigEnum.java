package com.pcbwx.ebes.enums;
/**
 * 系统配置枚举类
 * @author 王海龙
 * @version 1.0
 *
 */
public enum ConfigEnum {

	IS_ONLINE("is.online", "是否为线上环境"),
	//------------------ 配置文件配置项 -------------------------------
	SERVICE_MAIN_URL("service.main.url", "服务地址前缀"),
	DEBUG_WITHOUT_LOGIN("debug.without.login", "是否调试(免登录)"),
	POST_PATH("post.path", "顺丰url"),
	API_CODE("api.code", "接入编码"),
	CHECK_WORD("check.word", "密钥"),
	ADDRESS_URL("address.url", "请求全国行政区的url地址"),
	ADDRESS_CLOCK("address.clock", "请求全国行政区的时间"),
	MAILNO_FILEPATH("mailno.filepath", "请求全国行政区的时间"),
	ORDER_ID_HEAD("order.id.head", "订单号开头"),
	
	//-------------------邮件配置项-----------------------------------
	SEND_MAIL_TRANSPORT_PROTOCOL("send.mail.transport.protocol", "发送邮件的协议"),
	RECEIVE_MAIL_TRANSPORT_PROTOCOL("receive.mail.transport.protocol", "接受邮件的协议"),
	SEND_MAIL_SMTP_HOST("send.mail.smtp.host", "发送邮件的端口"),
	RECEIVE_MAIL_SMTP_HOST("receive.mail.smtp.host", "接受邮件的端口"),
	MAIL_ACCOUNT("mail.account", "邮件账号"),
	MAIL_PASSWORD("mail.password", "邮件密码"),
	MAIL_TARGET_ACCOUNT("mail.target.account", "收件邮箱"),
	INNER_MAIL("inner.mail", "转内网邮箱"),
	
	//------------------ config表配置项 -------------------------------

	//------------------ record_utils表记录项 -------------------------
	LAST_MAILNO_SYN_TIME("last_mailno_syn_time", "上次运单号同步时间"),
	LAST_CONTACT_SYN_TIME("last_contact_syn_time", "上次联系人同步时间"),
	LAST_RELOAD_TIME("last_reload_time", "上次缓存载入时间"),
	LAST_ADRESS_REFLASH_TIME("last_adress_reflash_time", "上次行政区更新时间"),
	ORDER_ID("order_id", "订单号");
	
	
	private String code;
	private String descr;
	
	private ConfigEnum(String code, String descr) {
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
