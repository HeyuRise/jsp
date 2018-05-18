package com.pcbwx.jsp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcbwx.jsp.dao.RoleAuthMapper;
import com.pcbwx.jsp.dao.UserAuthMapper;
import com.pcbwx.jsp.dao.UserRoleMapper;
import com.pcbwx.jsp.model.RoleAuth;
import com.pcbwx.jsp.model.UserAuth;
import com.pcbwx.jsp.model.UserRole;
import com.pcbwx.jsp.util.StringUtil;

/**
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，
 * 供Spring Security使用，用于权限校验。
 * 
 */
@Service("mySecurityMetadataSource")
@Transactional
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	private RequestMatcher pathMatcher;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleAuthMapper roleAuthMapper;
	@Autowired
	private UserAuthMapper userAuthMapper;

	public MySecurityMetadataSource() {}

	// 由spring调用
	public MySecurityMetadataSource(UserRoleMapper userRoleMapper) {
		loadResourceDefine();
	}
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}
	public boolean supports(Class<?> clazz) {
		return true;
	}
	public void getNewResourceMap() {
		logger.info("重载security的角色权限关系");
		loadResourceDefine();
	}
	
	// 拦截的url和角色名之间的关系
	private void loadResourceDefine() {
		/* url，角色集合 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = null;
		ConfigAttribute ca = null;
		String urlIndex = "index.html";
		// 判断是否为调试环境，如果为调试环境，不添加权限验证
		List<UserRole> userRoles = userRoleMapper.getRoles();
		List<RoleAuth> roleAuths = roleAuthMapper.load();
		for (UserRole userRole : userRoles) {
			String roleName = userRole.getRoleName();
			List<Integer> authIds = new ArrayList<Integer>();
			for (RoleAuth roleAuth : roleAuths) {
				if (Objects.equals(userRole.getId(), roleAuth.getRoleId())) {
					authIds.add(roleAuth.getAuthId());
				}
			}
			List<String> urls = new ArrayList<String>();
			for (Integer authId : authIds) {
				List<UserAuth> userAuths = userAuthMapper.selectByAuthType(authId);
				if (userAuths == null || userAuths.isEmpty()) {
					continue;
				}
				for (UserAuth userAuth : userAuths) {
					urls.add(userAuth.getUrl());
				}
			}
			atts = new HashSet<ConfigAttribute>();
			ca = new SecurityConfig(roleName);
			atts.add(ca);
			// 给每个角色添加默认权限
			insertAuth(urlIndex, ca, atts);
			for (String url : urls) {
				logger.info(roleName + "----------------" + url);
				if (StringUtil.isEmpty(url)) {
					continue;
				}
				insertAuth(url, ca, atts);
			}
		}
	}
	
	// 添加权限
	private void insertAuth(String url, ConfigAttribute ca, Collection<ConfigAttribute> atts) {
		if (resourceMap.containsKey(url)) {
			resourceMap.get(url).add(ca);
		} else {
			resourceMap.put(url, atts);
		}
	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		if (resourceMap == null) {
			loadResourceDefine();
		}
		FilterInvocation fi = ((FilterInvocation) object);
		// 资源url遍历
		Iterator<String> it = resourceMap.keySet().iterator();
		Collection<ConfigAttribute> returnList = new ArrayList<ConfigAttribute>();
		while (it.hasNext()) {
			String resURL = it.next();
			pathMatcher = new AntPathRequestMatcher(resURL);
			if (pathMatcher.matches(fi.getRequest())) {
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);
				returnList.addAll(returnCollection);
			}
		}
		if (returnList.size() != 0) {
			return returnList;
		} else {
			return null;
		}
	}
}