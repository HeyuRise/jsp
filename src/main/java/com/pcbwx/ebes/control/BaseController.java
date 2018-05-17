package com.pcbwx.ebes.control;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.pcbwx.ebes.enums.ErrorCodeEnum;

public class BaseController {

	protected static Map<String, Object> expResult;
	protected static final String MSG = "msg";
	protected static final String STATE = "state";
	
	public String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath(); 
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path;
		if (!basePath.endsWith("/")) {
			basePath = basePath + "/";
		}
		return basePath;
	}
	protected Map<String, Object> setResultInfo(Map<String, Object> resp, ErrorCodeEnum errorCode){
		resp.put("result", errorCode.getCode());
		resp.put("resultInfo", errorCode.getDescr());
		return resp;
	}
	protected Map<String, Object> setResultInfo(Map<String, Object> resp, ErrorCodeEnum errorCode, String errorDesc){
		resp.put("result", errorCode.getCode());
		resp.put("resultInfo", errorDesc);
		return resp;
	}
}
