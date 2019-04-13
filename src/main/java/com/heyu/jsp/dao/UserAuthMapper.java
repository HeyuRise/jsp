package com.heyu.jsp.dao;

import java.util.Date;
import java.util.List;

import com.heyu.jsp.model.UserAuth;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserAuthMapper extends BaseMapper<UserAuth>{
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from user_auth")
	Date selectLastRecordTime();
	
	List<UserAuth> load();
	
    List<UserAuth> selectByAuthType(@Param("authType") Integer authType);
    
    List<UserAuth> selectByAuthName(@Param("authName") String authName);
}