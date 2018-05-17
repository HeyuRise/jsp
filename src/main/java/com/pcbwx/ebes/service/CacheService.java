package com.pcbwx.ebes.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pcbwx.ebes.enums.DictionaryEnum;
import com.pcbwx.ebes.model.Dictionary;
import com.pcbwx.ebes.model.Menu;
import com.pcbwx.ebes.model.RoleAuth;
import com.pcbwx.ebes.model.UserAuth;
import com.pcbwx.ebes.model.UserRole;
import com.pcbwx.ebes.model.UserRoleRelation;


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
