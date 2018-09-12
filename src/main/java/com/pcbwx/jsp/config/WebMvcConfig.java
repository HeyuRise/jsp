package com.pcbwx.jsp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcbwx.jsp.common.DateConverter;
import com.pcbwx.jsp.common.NullSerializer;
import com.pcbwx.jsp.interceptor.PageFilter;
import com.pcbwx.jsp.interceptor.SessionInterceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * springWeb配置类
 * 
 * @author 孙贺宇
 * @date 2018-09-12
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcAutoConfiguration implements WebMvcConfigurer{

	/**
	 * 指定日期接口解析方式
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> patterns = new ArrayList<>();
		patterns.add("/html/**");
		patterns.add("/script/**");
		patterns.add("/print/**");
		patterns.add("/index.html");
		patterns.add("/swagger-ui.html");
		patterns.add("/webjars/**");
		patterns.add("/swagger-resources/**");
		patterns.add("/swagger-resources/**");
		registry.addInterceptor(new SessionInterceptor()).excludePathPatterns(patterns); 
	}

	/**
	 * 把返回Json中的null换为""
	 */
	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.getSerializerProvider().setNullValueSerializer(new NullSerializer());
		return objectMapper;
	}

	/**
	 * 添加过滤器
	 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public FilterRegistrationBean<PageFilter> filterRegistrationBean() {
		FilterRegistrationBean<PageFilter> registrationBean = new FilterRegistrationBean<PageFilter>();
		PageFilter loginFilter = new PageFilter();
		registrationBean.setFilter(loginFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/login");
		urlPatterns.add("*.html");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

    /**
     * mybatis配置
     * @return
     */
	@Bean
	ConfigurationCustomizer mybatisConfig(){
		return configuration -> {
		    // setting
			configuration.setUseGeneratedKeys(true);
			configuration.setCacheEnabled(true);
			configuration.setLazyLoadingEnabled(true);
			configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
            configuration.setAggressiveLazyLoading(true);

            // typeAlias
            configuration.getTypeAliasRegistry().registerAliases("com.pcbwx.jsp.bean");
		};
	}
	
}
