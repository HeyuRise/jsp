
package com.pcbwx.jsp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;
/**
 * 程序启动类
 * 
 * @author 孙贺宇
 *
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.pcbwx.jsp.dao")
public class SystemStart extends SpringBootServletInitializer {
	// springBoot配置文件名字
	private static final String FILENAME = "spring.properties";
	// 系统英文简写
	public final static String MYSYSTEMCODE = "jsp";

	public static void main(String[] args) throws Exception {
		// 指定配置文件
		Properties properties = new Properties();
		String configFile = System.getenv("CONFIG_SPACE") + "/" + MYSYSTEMCODE + "/" + FILENAME;
		InputStream in = new FileInputStream(new File(configFile));
		properties.load(in);
		SpringApplication springApplication = new SpringApplication(SystemStart.class);
		springApplication.setDefaultProperties(properties);
		springApplication.run(args);
		log.info("系统已经启动");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SystemStart.class);
	}

}
