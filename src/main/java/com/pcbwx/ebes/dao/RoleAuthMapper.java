package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.ebes.model.RoleAuth;

public interface RoleAuthMapper extends BaseMapper<RoleAuth>{

	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from role_auth")
	Date selectLastRecordTime();
	
    List<RoleAuth> load();
    
    /**
     * 按角色id查找
     * @param roleId
     * @return
     */
    List<RoleAuth> selectByRoleId(@Param("roleId") Integer roleId);
    
    /**
     * 按角色id删除
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(@Param("roleId") Integer roleId);
    
    /**
     * 按权限id集合查找
     * @param authIds
     * @return
     */
    List<RoleAuth> selectByAuthIds(@Param("authIds") List<Integer> authIds);
}