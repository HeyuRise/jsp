package com.pcbwx.jsp.bean;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pcbwx.jsp.enums.ErrorCodeEnum;

@Component
@Scope("prototype")
@Data
public class MyResponse<T>{

	/**
	 * 返回接口状态码
	 */
	private Integer code;

	/**
	 * 接口信息
	 */
	private String msg;

	/**
	 * 返回接口数据
	 */
	private T data;
	
	/**
	 * 重载response
	 */
	public void reset() {
		this.code = null;
		this.msg = null;
		this.data = null;
	}
	
	/**
	 * 通过请求状态enum类来设置response
	 * @param errorCodeEnum
	 */
	public void setCodeAndMsg(ErrorCodeEnum errorCodeEnum) {
		this.code = errorCodeEnum.getCode();
		this.msg = errorCodeEnum.getDescr();
	}
	
	/**
	 * 通过请求状态码和错误消息来设置response
	 * @param errorCodeEnum
	 * @param errorMsg
	 */
	public void setFalse(ErrorCodeEnum errorCodeEnum, String errorMsg) {
		this.code = errorCodeEnum.getCode();
		this.msg = errorMsg;
	}
	
	/**
	 * 设置请求成功返回信息
	 * @param data
	 */
	public void setSuccess(T data) {
		this.code = ErrorCodeEnum.SUCCESS.getCode();
		this.msg = ErrorCodeEnum.SUCCESS.getDescr();
		this.data = data;
	}
}
