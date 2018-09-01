package com.pcbwx.jsp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.pcbwx.jsp.dao.RoleAuthMapper;
import com.pcbwx.jsp.dao.UserAuthMapper;
import com.pcbwx.jsp.dao.UserRoleMapper;
import com.pcbwx.jsp.model.RoleAuth;
import com.pcbwx.jsp.model.UserAuth;
import com.pcbwx.jsp.model.UserRole;
import com.pcbwx.jsp.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 加载页面资源
 * @author heyu
 */
@Slf4j
@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	private RequestMatcher pathMatcher;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleAuthMapper roleAuthMapper;
	@Autowired
	private UserAuthMapper userAuthMapper;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<>();
	}

	@Override
    public boolean supports(Class<?> clazz) {
		return true;
	}

    /**
     * 重载security的角色权限关系
     */
	public void reloadResource() {
		log.info("重载security的角色权限关系");
        loadResource();
	}

	@Override
    public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		if (resourceMap == null) {
            loadResource();
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

    /**
     * 加载拦截的url和角色名之间的关系
     */
    private void loadResource() {
        /* url，角色集合 */
        resourceMap = new HashMap<>(100);
        Collection<ConfigAttribute> list;
        ConfigAttribute ca;
        // 判断是否为调试环境，如果为调试环境，不添加权限验证
        List<UserRole> userRoles = userRoleMapper.getRoles();
        List<RoleAuth> roleAuthList = roleAuthMapper.load();
        for (UserRole userRole : userRoles) {
            String roleName = userRole.getRoleName();
            List<String> urls = this.getUrlsByRoleId(userRole.getId(), roleAuthList);
            for (String url : urls) {
                list = new HashSet<>();
                ca = new SecurityConfig(roleName);
                log.info(roleName + "----------------" + url);
                if (StringUtil.isEmpty(url)) {
                    continue;
                }
                insertAuth(url, ca, list);
            }
        }
    }

	private List<String> getUrlsByRoleId(Integer roleId, List<RoleAuth> roleAuthList){
        List<Integer> authIds = new ArrayList<>();
        for (RoleAuth roleAuth : roleAuthList) {
            if (Objects.equals(roleId, roleAuth.getRoleId())) {
                authIds.add(roleAuth.getAuthId());
            }
        }
        List<String> urls = new ArrayList<>();
        for (Integer authId : authIds) {
            List<UserAuth> userAuthList = userAuthMapper.selectByAuthType(authId);
            for (UserAuth userAuth : userAuthList) {
                urls.add(userAuth.getUrl());
            }
        }
        return urls;
    }

    private void insertAuth(String url, ConfigAttribute ca, Collection<ConfigAttribute> list) {
        if (resourceMap.containsKey(url)) {
            resourceMap.get(url).add(ca);
        } else {
            resourceMap.put(url, list);
        }
    }
}