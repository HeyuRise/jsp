package com.heyu.jsp.bean;

import java.util.Collection;

import com.heyu.jsp.model.WxtbUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.heyu.jsp.util.DataUtil;

public class User extends WxtbUser implements UserDetails {

	private static final long serialVersionUID = -5437876237947695702L;

	private Collection<? extends GrantedAuthority> authorities;

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.getHashedPassword();
	}

	public boolean isAccountNonExpired() {
		return DataUtil.equals(getAccountNonExpired(), 1) ? true : false;
	}

	public boolean isCredentialsNonExpired() {
		return DataUtil.equals(getCredentialsNonExpired(), 1) ? true : false;
	}

	public boolean isAccountNonLocked() {
		return DataUtil.equals(getAccountNonLocked(), 1) ? true : false;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnable() == 1 ? true : false;
	}
}
