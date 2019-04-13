package com.heyu.jsp.dao;

import java.util.Date;
import java.util.List;

import com.heyu.jsp.model.WxtbUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WxtbUserMapper extends BaseMapper<WxtbUser> {

	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from wxtb_user")
	Date selectLastRecordTime();

	/**
	 * 获取全部可用用户
	 * @return
	 */
	List<WxtbUser> load();
	
	/**
	 * 按用户名查找
	 * @param account
	 * @return
	 */
	WxtbUser selectByAccount(@Param("account") String account);
	
	/**
	 * 按用户名删除
	 * @param account
	 * @return
	 */
	Integer deleteByAccount(@Param("account") String account);

	/**
	 * 按条件查找
	 * @param account
	 * @param name
	 * @param enable
	 * @param department
	 * @param accounts
	 * @return
	 */
	List<WxtbUser> selectByCondition(@Param("account") String account,
			@Param("name") String name, @Param("enable") Integer enable,
			@Param("department") String department,
			@Param("accounts") List<String> accounts);
}