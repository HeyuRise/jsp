package com.pcbwx.jsp.exception;

/**
 * 异常基类
 * @author 孙贺宇
 *
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -7150191146206885734L;

	/**
	 * 错误编码
	 */
	private  String errorCode; 

	private String errorMsg;
	
	public BusinessException(){
		
	}
	public BusinessException(Throwable t){
		super(t);
		if(t instanceof BusinessException){
			this.errorCode = ((BusinessException)t).getErrorCode();
			this.errorMsg = ((BusinessException)t).getErrorMsg();
		}else{
			this.errorCode = "-99";
			this.errorMsg = t.getMessage();
		}
	}
	/**
	 * 自行抛出异常
	 * @param exceptionType
	 * @param msg
	 */
	public BusinessException(ExceptionType exceptionType){
		super(exceptionType.getErrorMsg());
		this.errorCode = exceptionType.getErrorCode();
		this.errorMsg = exceptionType.getErrorMsg();
		
	}
	/**
	 * 自行抛出异常
	 * @param exceptionType
	 * @param msg
	 */
	public BusinessException(ExceptionType exceptionType, String msg){
		super(exceptionType.getErrorMsg() + msg);
		this.errorCode = exceptionType.getErrorCode();
		this.errorMsg = exceptionType.getErrorMsg() + msg;
		
	}

	/**
	 * 运行时异常封装
	 * @param exceptionType
	 * @param msg
	 * @param cause
	 */
	public BusinessException(ExceptionType exceptionType, String msg, Throwable cause){
		super(exceptionType.getErrorMsg() + msg, cause);
		this.errorCode = exceptionType.getErrorCode();
		this.errorMsg = exceptionType.getErrorMsg();
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
	public String toString(){
		return this.errorMsg;
	}
}
