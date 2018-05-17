package com.pcbwx.ebes.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.pcbwx.ebes.security.MySecurityFilter;
import com.pcbwx.ebes.security.MySecurityMetadataSource;
import com.pcbwx.ebes.security.MyUserDetailServiceImpl;
import com.pcbwx.ebes.util.MD5Util;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MySecurityMetadataSource securityMetadataSource;
	@Autowired
	private MySecurityFilter mySecurityFilter;
	@Autowired
	private MyUserDetailServiceImpl userDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // 关闭csrf
		http.authorizeRequests().anyRequest().authenticated() // 任何请求,登录后可以访问
				.and().formLogin().loginPage("/login").failureUrl("/login").defaultSuccessUrl("/index.html").permitAll() // 登录页面用户任意访问
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll(); // 注销行为任意访问
				// 默认登录页面
//				.and().formLogin().defaultSuccessUrl("/index.html").permitAll() 
//				.and().logout().logoutUrl("/logout").permitAll(); 
		http.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/script/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
