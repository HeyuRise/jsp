
package com.pcbwx.ebes;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.pcbwx.ebes.common.ConfigProperties;
import com.pcbwx.ebes.interceptor.CheckLoginFilter;


@SpringBootApplication
@MapperScan(basePackages = "com.pcbwx.ebes.dao")
public class SystemStart extends SpringBootServletInitializer {
	private static Logger logger = LoggerFactory.getLogger(SystemStart.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SystemStart.class);
	}

	public static void main(String[] args) throws Exception {
		// 加载config配置文件
		ConfigProperties.load();
		// 指定配置文件
		Properties properties = new Properties();
		String fileName = "application.properties";
		String configFile = System.getenv("CONFIG_SPACE") + "/" + ConfigProperties.getMySystemCode() + "/" + fileName;
		InputStream in = new FileInputStream(new File(configFile));
		properties.load(in);
		SpringApplication springApplication = new SpringApplication(SystemStart.class);
		springApplication.setDefaultProperties(properties);
		springApplication.run(args);
		logger.info("系统已经启动");
	}
	
	
	@Bean  
	@Order(Integer.MAX_VALUE) // 指定过滤器顺序
    public FilterRegistrationBean<CheckLoginFilter>  filterRegistrationBean() {  
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
