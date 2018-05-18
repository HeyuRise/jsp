package com.pcbwx.jsp.security;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcbwx.jsp.bean.User;
import com.pcbwx.jsp.dao.WxtbUserMapper;
import com.pcbwx.jsp.model.UserRole;
import com.pcbwx.jsp.model.WxtbUser;
import com.pcbwx.jsp.service.CacheService;
import com.pcbwx.jsp.service.SupportService;
import com.pcbwx.jsp.util.DataUtil;
import com.pcbwx.jsp.util.StringUtil;

@Service("myUserDetailServiceImpl")
@Transactional
public class MyUserDetailServiceImpl implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);
	
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;
	
	@Autowired
	private WxtbUserMapper wxtbUserMapper;
	
	
	@Override
	//通过cas返回的用户名，重载为系统中自定义的用户
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(StringUtil.isEmpty(username)){
			throw new UsernameNotFoundException("用户名不能为空！");	
		}
		WxtbUser wxtbUser = null;
		try {
			wxtbUser = wxtbUserMapper.selectByAccount(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(wxtbUser == null){
			throw new UsernameNotFoundException("不存在该用户！ ");	
		}else if (!Objects.equals(1, wxtbUser.getEnable())) {
			throw new UsernameNotFoundException("您已经被禁用！ ");	
		}
		Set<Integer> authSet = supportService.getUserAuths(username);
		if (authSet == null || authSet.isEmpty()) {
			throw new UsernameNotFoundException("对不起，您没有该系统权限 ");	
		}
		User user = (User) DataUtil.fatherToChild(wxtbUser, User.class);
		Set<GrantedAuthority> authorities = obtionGrantedAuthority(user);
		user.setAuthorities(authorities);
        return user;
	}
	
	private Set<GrantedAuthority> obtionGrantedAuthority(User user){
		Set<GrantedAuthority> roleSet = new HashSet<GrantedAuthority>();
		final List<UserRole> userRoles = cacheService.getUserRole(user.getAccount());
	    for(UserRole role : userRoles) {
	        roleSet.add(new SimpleGrantedAuthority(role.getRoleName()));
	    }	
        return roleSet;
	}
}



