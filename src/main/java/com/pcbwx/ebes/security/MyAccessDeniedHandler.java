package com.pcbwx.ebes.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.alibaba.fastjson.JSONObject;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
	private String accessDeniedUrl;

	public MyAccessDeniedHandler() {
	}

	public MyAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (isAjax) {
			JSONObject json = new JSONObject();
			json.put("state", "false");
			String contentType = "application/json";
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.write(json.toJSONString());
			out.flush();
			out.close();
		} else {
			JSONObject json = new JSONObject();
			json.put("abc", "11111");
			String contentType = "application/json";
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.write(json.toJSONString());
			out.flush();
			out.close();
		}

	}

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

}
