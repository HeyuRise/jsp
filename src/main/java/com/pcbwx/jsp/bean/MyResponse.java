package com.pcbwx.jsp.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pcbwx.jsp.enums.ErrorCodeEnum;

@Component
@Scope("prototype")
public class MyResponse<T>{
	
	// 返回接口状态码
	private Integer code;
	// 接口信息
	private String msg;
	// 返回接口数据
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
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
