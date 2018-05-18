package com.pcbwx.jsp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.jsp.model.UserRoleRelation;

public interface UserRoleRelationMapper extends BaseMapper<UserRoleRelation> {
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from user_role_relation")
	Date selectLastRecordTime();
	
	/**
	 * 获取可用角色用户关系集合
	 * @return
	 */
	List<UserRoleRelation> load();
	
	/**
	 * 按角色id集合查找
	 * @param ids
	 * @return
	 */
	List<UserRoleRelation> selectByIds(@Param("ids") List<Integer> ids);
	
	/**
	 * 按用户账号删除
	 * @param account
	 * @return
	 */
	Integer deleteByAccount(@Param("account") String account);
}