package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.ebes.model.Address;

public interface AddressMapper extends BaseMapper<Address>{

	@Select("select GREATEST(COALESCE(max(creat_time)),COALESCE(max(update_time),0)) from address")
	Date selectLastRecordTime();
	
	/**
	 * 按条件查找
	 * @param province
	 * @param city
	 * @param county
	 * @return
	 */
	List<Address> selectByCondition(@Param("province") String province,
			@Param("city") String city, @Param("county") String county);
}