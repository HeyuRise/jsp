package com.pcbwx.jsp.exception;

/**
 * 异常类型定义
 * @author 王海龙
 *
 */
public enum ExceptionType {

	EXCEPTION_000("0", "校验不通过："),
	EXCEPTION_001("-1", "无效请求:"),
	EXCEPTION_100("-100", "数据库连接异常："),
	EXCEPTION_200("-200", "SQL执行异常："),
	EXCEPTION_300("-300", "数据处理异常："),
	EXCEPTION_400("-400", "文件处理异常："),
	EXCEPTION_500("-500", "网络连接异常："),
	EXCEPTION_600("-600", "发送邮件异常："),
	EXCEPTION_700("-600", "发送短信异常：");
	
    private String errorCode;
    private String errorMsg;
    ExceptionType(String errorCode, String errorMsg) {
    	this.errorCode = errorCode;
    	this.errorMsg = errorMsg;
    }
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
    
}
