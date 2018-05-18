package com.pcbwx.jsp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pcbwx.jsp.enums.DictionaryEnum;
import com.pcbwx.jsp.model.Dictionary;
import com.pcbwx.jsp.model.Menu;
import com.pcbwx.jsp.model.RoleAuth;
import com.pcbwx.jsp.model.UserAuth;
import com.pcbwx.jsp.model.UserRole;
import com.pcbwx.jsp.model.UserRoleRelation;


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
