
package com.heyu.jsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 程序启动类
 * 
 * @author 孙贺宇
 *
 */
@MapperScan(basePackages = "com.heyu.jsp.dao")
@SpringBootApplication
@EnableTransactionManagement
public class SystemStart {

	public static void main(String[] args){
		SpringApplication.run(SystemStart.class, args);
	}

}
