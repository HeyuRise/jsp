package com.pcbwx.jsp.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcbwx.jsp.common.DateConverter;
import com.pcbwx.jsp.common.NullSerializer;
import com.pcbwx.jsp.interceptor.CheckLoginFilter;
import com.pcbwx.jsp.interceptor.SessionInterceptor;

/**
 * springWeb配置类
 * 
 * @author 孙贺宇
 *
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcAutoConfiguration implements WebMvcConfigurer{

	// 把返回Json中的null换为""
	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.getSerializerProvider().setNullValueSerializer(new NullSerializer());
		return objectMapper;
	}
	
	// 添加日期解析器
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}
	
	// 添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = new ArrayList<>();
		patterns.add("/html/**");
		patterns.add("/script/**");
		patterns.add("/print/**");
		patterns.add("/index.html");
		registry.addInterceptor(new SessionInterceptor()).excludePathPatterns(patterns); 
	}
	
	// 添加过滤器
	@Bean
	@Order(Integer.MAX_VALUE) // 指定过滤器顺序（小一级为Integer.MAX_VALUE - 1）
	public FilterRegistrationBean<CheckLoginFilter> filterRegistrationBean() {
		FilterRegistrationBean<CheckLoginFilter> registrationBean = new FilterRegistrationBean<CheckLoginFilter>();
		CheckLoginFilter loginFilter = new CheckLoginFilter();
		registrationBean.setFilter(loginFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/login");
		urlPatterns.add("*.html");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	
}
