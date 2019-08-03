package com.heyu.jsp.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.heyu.jsp.security.MySecurityFilter;
import com.heyu.jsp.security.MySecurityMetadataSource;
import com.heyu.jsp.util.MD5Util;
/**
 * springSecurity配置类
 * 
 * @author 孙贺宇
 * @date 2018-09-12
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MySecurityMetadataSource securityMetadataSource;
	@Autowired
	private MySecurityFilter mySecurityFilter;
	@Autowired
	private UserDetailsService userDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 关闭csrf
		http.authorizeRequests()
				// 添加默认权限
				.antMatchers("/index", "/html/**").authenticated()
				// .anyRequest().authenticated() // 任何请求,登录后可以访问
				.and().formLogin().loginPage("/login").failureUrl("/login").defaultSuccessUrl("/index").permitAll()
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
	}
	
	@Override
	public void configure(WebSecurity web){
		// swagger路径
		web.ignoring().antMatchers("/webjars/**", "/swagger-resources/**", "/v2/**", "/swagger-ui.html");
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置密码加密方法
		auth.userDetailsService(userDetailService).passwordEncoder(new PasswordEncoder() {

			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return Objects.equals(encodedPassword, MD5Util.encode((String) rawPassword));
			}
		});
	}

}
