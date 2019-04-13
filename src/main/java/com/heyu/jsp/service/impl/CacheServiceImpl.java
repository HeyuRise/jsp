package com.heyu.jsp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.heyu.jsp.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.heyu.jsp.enums.DictionaryEnum;
import com.heyu.jsp.model.Dictionary;
import com.heyu.jsp.model.Menu;
import com.heyu.jsp.model.RoleAuth;
import com.heyu.jsp.model.UserAuth;
import com.heyu.jsp.model.UserRole;
import com.heyu.jsp.model.UserRoleRelation;
import com.heyu.jsp.util.DataUtil;

/**
 * 缓存服务
 * @author heyu
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {
	
	private static Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
	
	// type#id,Dictionary / type|code,Dictionary
	private Map<String, Dictionary> dictionaryCache = new HashMap<String, Dictionary>();
	private Map<String, List<Dictionary>> dictionaryByTypeCache = new HashMap<String, List<Dictionary>>();	

	
	private Map<Integer, UserRole> userRoleCache = new HashMap<Integer, UserRole>();
	private Map<String, List<UserRoleRelation>> userRoleRelationCache = new HashMap<String, List<UserRoleRelation>>();
	private Map<Integer, UserAuth> userAuthCache = new HashMap<Integer, UserAuth>();
	private Map<Integer, RoleAuth> roleAuthCache = new HashMap<Integer, RoleAuth>();
	private Map<Integer, Menu> menuCache = new HashMap<Integer, Menu>();	
		
	@Override
	public void reloadDictionary(List<Dictionary> dictionaries) {
		Map<String, Dictionary> newDictionaryCache = new HashMap<String, Dictionary>();
		if (dictionaries != null && !dictionaries.isEmpty()) {
			for (Dictionary record : dictionaries) {
				if (record.getInnerId() != null) {
					String key = record.getType() + "#" + record.getInnerId();
					newDictionaryCache.put(key, record);
				}
				if (record.getInnerCode() != null) {
					String key = record.getType() + "|" + record.getInnerCode();
					newDictionaryCache.put(key, record);
				}
			}
			dictionaryCache = newDictionaryCache;
			logger.info("字典缓存条数:" + dictionaries.size());
			
			try {
				dictionaryByTypeCache = DataUtil.list2mapList(dictionaries, Dictionary.class, "type");
				logger.info("字典类别缓存条数:" + dictionaryByTypeCache.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void reloadUserRole(List<UserRole> records) {
		logger.info("载入系统用户角色");
		Map<Integer, UserRole> tempCache = new HashMap<Integer, UserRole>();
		try {
			tempCache = DataUtil.list2map(records, UserRole.class, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		userRoleCache = tempCache;
		logger.info("载入系统功能模块条数=" + userRoleCache.size());		
	}
	@Override
	public void reloadUserRoleRelation(List<UserRoleRelation> records) {
		logger.info("载入系统用户角色关系");
		Map<String, List<UserRoleRelation>> tempCache = new HashMap<String, List<UserRoleRelation>>();
		try {
			tempCache = DataUtil.list2mapList(records, UserRoleRelation.class, "account");
		} catch (Exception e) {
			e.printStackTrace();
		}
		userRoleRelationCache = tempCache;
		logger.info("载入系统用户角色关系条数=" + userRoleRelationCache.size());
	}

	@Override
	public void reloadUserAuth(List<UserAuth> records) {
		logger.info("载入权限");
		Map<Integer, UserAuth> tempCache = new HashMap<Integer, UserAuth>();
		try {
			tempCache = DataUtil.list2map(records, UserAuth.class, "authType");
		} catch (Exception e) {
			e.printStackTrace();
		}
		userAuthCache = tempCache;
		logger.info("载入权限个数" + userAuthCache.size());
	}

	@Override
	public void reloadRoleAuth(List<RoleAuth> records) {
		logger.info("载入角色权限关系");
		Map<Integer, RoleAuth> tempCache = new HashMap<Integer, RoleAuth>();
		try {
			tempCache = DataUtil.list2map(records, RoleAuth.class, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		roleAuthCache = tempCache;
		logger.info("载入角色权限关系条数" + roleAuthCache.size());
	}
	
	@Override
	public void reloadMenu(List<Menu> records) {
		logger.info("载入侧边栏信息");
		Map<Integer, Menu> tempCache = new HashMap<Integer, Menu>();
		try {
			tempCache = DataUtil.list2map(records, Menu.class, "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		menuCache = tempCache;
		logger.info("载入侧边栏信息" + menuCache.size() + "条");
		
	}

	//----------------------------------------------------------------------

	private String getDictionaryKey(DictionaryEnum type, Integer innerId) {
		String dictionaryKey = type.getCode() + "#" + innerId;
		return dictionaryKey;
	}

	private String getDictionaryKey(DictionaryEnum type, String innerCode) {
		String dictionaryKey = type.getCode() + "|" + innerCode;
		return dictionaryKey;
	}

	@SuppressWarnings("unused")
	private String getDictionaryKeyOfValue(DictionaryEnum type, String value) {
		String dictionaryKey = type.getCode() + "?" + value;
		return dictionaryKey;
	}

	@Override
	public Dictionary getDictionary(DictionaryEnum type, Integer innerId) {
		String key = this.getDictionaryKey(type, innerId);
		return dictionaryCache.get(key);
	}

	@Override
	public Dictionary getDictionary(DictionaryEnum type, String innerCode) {
		String key = this.getDictionaryKey(type, innerCode);
		return dictionaryCache.get(key);
	}

	@Override
	public List<Dictionary> getDictionarys(DictionaryEnum type) {
		return dictionaryByTypeCache.get(type.getCode());
	}
	
	@Override
	public Map<Integer, UserRole> getUserRoles() {
		return userRoleCache;
	}

	@Override
	public Map<Integer, RoleAuth> getRoleAuth() {
		return roleAuthCache;
	}
	
	@Override
	public Map<Integer, Menu> getMenu() {
		return menuCache;
	}
	
	@Override
	public List<UserRole> getUserRole(String account) {
		List<UserRole> roles = new ArrayList<UserRole>();
		final List<UserRoleRelation> relations = userRoleRelationCache.get(account);
		if (relations == null || relations.isEmpty()) {
			return roles;
		}
		for (UserRoleRelation ur : relations) {
			final UserRole userRole = userRoleCache.get(ur.getRoleId());
			if (userRole == null || userRole.getEnable() == 0) {
				continue;
			}
			roles.add(userRole);
		}
		return roles;
	}

	@Override
	public Set<Integer> getRoleIds(String account) {
		Set<Integer> roleIds = new HashSet<Integer>();
		final List<UserRoleRelation> relations = userRoleRelationCache.get(account);
		if (relations == null || relations.isEmpty()) {
			return roleIds;
		}
		for (UserRoleRelation ur : relations) {
			roleIds.add(ur.getRoleId());
		}
		return roleIds;
	}
	

}
