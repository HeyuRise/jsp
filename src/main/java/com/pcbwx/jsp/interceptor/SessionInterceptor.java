package com.pcbwx.jsp.interceptor;

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
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
								Exception exception) {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
						   ModelAndView modelAndView) {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
		// 打印请求信息
		String reqUrl = request.getRequestURL().toString();
		String paramStr = request.getQueryString();
		logger.info(request.getMethod() + " >> " + reqUrl + "?" + paramStr);
		return true;
	}

}
