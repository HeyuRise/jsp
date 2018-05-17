package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.ebes.model.Service;

public interface ServiceMapper extends BaseMapper<Service>{
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from service")
	Date selectLastRecordTime();
	
	/**
	 * 按条件查找
	 * @param serviceName
	 * @return
	 */
	List<Service> selectByCondition(@Param("serviceName") String serviceName);
	
	/**
	 * 按增值服务名字查找
	 * @param serviceName
	 * @return
	 */
	Service selectByService(@Param("serviceName") String serviceName);
	
	/**
	 * 查找可用增值服务
	 * @return
	 */
	List<Service> selectByStatus();
}