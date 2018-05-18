package com.pcbwx.jsp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.jsp.model.UserAuth;

public interface UserAuthMapper extends BaseMapper<UserAuth>{
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from user_auth")
	Date selectLastRecordTime();
	
	List<UserAuth> load();
	
    List<UserAuth> selectByAuthType(@Param("authType") Integer authType);
    
    List<UserAuth> selectByAuthName(@Param("authName") String authName);
}