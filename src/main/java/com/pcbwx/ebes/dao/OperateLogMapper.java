package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.OperateLog;

public interface OperateLogMapper extends BaseMapper<OperateLog>{
	
	Integer getSelectByConditionNum(@Param("keyWord") String keyWord, 
									@Param("startTime") Date startTime, 
									@Param("stopTime") Date stopTime);
	
	List<OperateLog> selectByCondition(@Param("keyWord") String keyWord, 
										@Param("startTime") Date startTime, 
										@Param("stopTime") Date stopTime,
										@Param("start") Integer start,
										@Param("pageSize") Integer pageSize);
}