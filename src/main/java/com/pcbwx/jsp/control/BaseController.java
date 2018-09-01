package com.pcbwx.jsp.control;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcbwx.jsp.bean.MyResponse;

/**
 * controller基类
 * @author 孙贺宇
 * @date 2018-09-31
 * @version 1.0
 *
 */
public class BaseController {

	@Autowired
	protected MyResponse<Object> response;
	
}
