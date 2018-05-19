package com.pcbwx.jsp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pcbwx.jsp.converter.DateConverter;
import com.pcbwx.jsp.interceptor.SessionInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = new ArrayList<>();
		patterns.add("/html/**");
		patterns.add("/script/**");
		patterns.add("/print/**");
		patterns.add("/index.html");
		registry.addInterceptor(new SessionInterceptor()).excludePathPatterns(patterns); 
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}

}
