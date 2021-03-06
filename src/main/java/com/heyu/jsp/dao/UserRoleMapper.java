package com.heyu.jsp.dao;

import java.util.Date;
import java.util.List;

import com.heyu.jsp.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author heyu
 */
@Mapper
@Component
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Options(useGeneratedKeys = true)
	@Override
	int insertSelective(UserRole record);

	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from user_role")
	Date selectLastRecordTime();
	
	/**
	 * 获取可用可现实的角色
	 * @return
	 */
	List<UserRole> load();
	
	/**
	 * 获取可用角色
	 * @return
	 */
	@Options()
	List<UserRole> getRoles();
	
	/**
	 * 按角色名字查找
	 * @param roleName
	 * @return
	 */
	List<UserRole> selectByRoleName(@Param("roleName") String roleName);
}