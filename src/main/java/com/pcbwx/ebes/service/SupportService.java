package com.pcbwx.ebes.service;

import java.util.Set;

import com.pcbwx.ebes.enums.ClickEnum;


/**
 * 工具模块业务接口
 * 
 * @author 王海龙
 *
 */
public interface SupportService {
	/**
	 * 初始化内存数据
	 * @return
	 */
	boolean doReloadCache();	
	/**
	 * 重载角色权限关系
	 */
	void reloadRoleAuth();	
	/**
	 * 重载角色
	 */
	void reloadUserRole();
	/**
	 * 获取用户的全部权限id集合
	 * @param account
	 * @return
	 */
	Set<Integer> getUserAuths(String account);	
	/**
	 * 查看是否可点击
	 * @param account
	 * @param clickEnum
	 * @return
	 */
	Integer ebableClick(String account, ClickEnum clickEnum);
}
