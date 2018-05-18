package com.pcbwx.jsp.enums;

/**
 * 权限枚举类
 * 
 * @author 王海龙
 *
 */
public enum AuthorityEnum {

		ROLE_LOGINOUT("ROLE_LOGINOUT", "登录和注销系统权限"), 
		ROLE_ROOM_VIEW("ROLE_ROOM_VIEW", "会议室浏览权限"), 
		ROLE_ROOM_AT_BOOKING_VIEW("ROLE_ROOM_AT_BOOKING_VIEW", "会议室浏览权限_会议预定"), 
		ROLE_ROOM_EDIT("ROLE_ROOM_EDIT","会议室编辑权限"), 
		ROLE_ROOM_DELETE("ROLE_ROOM_DELETE", "会议室删除权限"), 
		ROLE_BOOKING_VIEW("ROLE_BOOKING_VIEW", "会议室预订浏览权限"), 
		ROLE_BOOKING_EDIT("ROLE_BOOKING_EDIT", "会议室预订编辑权限"),
		ROLE_BOOKING_DELETE("ROLE_BOOKING_DELETE", "会议室预订删除权限"), 
		ROLE_BOOKING_APPROVE("ROLE_BOOKING_APPROVE", "会议室预订审核权限"), 
		ROLE_BOOKING_SUPPORT("ROLE_BOOKING_SUPPORT", "会议室支持权限"), 
		ROLE_PERSON_VIEW("ROLE_PERSON_VIEW", "联系人浏览权限"), 
		ROLE_PERSON_AT_BOOKING_VIEW("ROLE_PERSON_AT_BOOKING_VIEW", "联系人浏览权限_会议预定"), 
		ROLE_PERSON_EDIT("ROLE_PERSON_EDIT", "联系人编辑权限"), 
		ROLE_PERSON_DELETE("ROLE_PERSON_DELETE", "联系人删除权限"), 
		ROLE_LOG_VIEW("ROLE_LOG_VIEW","日志浏览权限"), 
		ROLE_CONFIG_VIEW("ROLE_CONFIG_VIEW", "配置浏览权限"), 
		ROLE_CONFIG_EDIT("ROLE_CONFIG_EDIT", "配置编辑权限"),
		ROLE_DEVICE_VIEW("ROLE_DEVICE_VIEW","会议室设备浏览权限"),
		ROLE_DEVICE_EDIT("ROLE_DEVICE_EDIT","会议室设备编辑权限"),
		ROLE_DEVICE_DELETE("ROLE_DEVICE_DELETE","会议室设备删除权限"),
		ROLE_AD_SYNCHRONIZE("ROLE_AD_SYNCHRONIZE","AD联系人同步权限");

	
		private String code; // 权限代码
		private String desc; // 权限描述
	
		private AuthorityEnum(String code, String desc) {
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
