package com.pcbwx.jsp.config;

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

import com.pcbwx.jsp.security.MySecurityFilter;
import com.pcbwx.jsp.security.MySecurityMetadataSource;
import com.pcbwx.jsp.util.MD5Util;
/**
 * springSecurity配置类
 * 
 * @author 孙贺宇
 *
 */
@EnableWebSecurity
@Configuration
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
				// .anyRequest().authenticated() // 任何请求,登录后可以访问
				.and().formLogin().loginPage("/login").failureUrl("/login").defaultSuccessUrl("/index.html").permitAll() // 登录页面用户任意访问
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll(); // 注销行为任意访问
		http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/oiss_web_service/**");
		// 排除不需要拦截的路径
		web.ignoring().antMatchers("/script/**");
		web.ignoring().antMatchers("/eda/**");
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
