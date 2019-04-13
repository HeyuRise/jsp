package com.heyu.jsp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.heyu.jsp.enums.DictionaryEnum;
import com.heyu.jsp.model.Dictionary;
import com.heyu.jsp.model.Menu;
import com.heyu.jsp.model.RoleAuth;
import com.heyu.jsp.model.UserAuth;
import com.heyu.jsp.model.UserRole;
import com.heyu.jsp.model.UserRoleRelation;


public interface CacheService {
	void reloadDictionary(List<Dictionary> dictionarys);

	void reloadUserRole(List<UserRole> records);
	void reloadUserRoleRelation(List<UserRoleRelation> records);
	void reloadUserAuth(List<UserAuth> records);
	void reloadRoleAuth(List<RoleAuth> records);
	void reloadMenu(List<Menu> records);
	//--------------------------------------------------

	Dictionary getDictionary(DictionaryEnum type, Integer innerId);
	Dictionary getDictionary(DictionaryEnum type, String innerCode);
	
	List<Dictionary> getDictionarys(DictionaryEnum type);
	
	List<UserRole> getUserRole(String account);
	Set<Integer> getRoleIds(String account);
	Map<Integer, UserRole> getUserRoles();
	Map<Integer, RoleAuth> getRoleAuth();
	Map<Integer, Menu> getMenu();
} 
