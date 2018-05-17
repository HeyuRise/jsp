package com.pcbwx.ebes.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 请求拦截器
 * 
 * @author 孙贺宇
 * 
 */
public class SessionInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// 打印请求信息
		String reqUrl = request.getRequestURL().toString();
		String paramStr = request.getQueryString();
		logger.info(request.getMethod() + " >> " + reqUrl + "?" + paramStr);
		return true;
	}

}
