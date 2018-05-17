package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.ebes.model.Config;

public interface ConfigMapper extends BaseMapper<Config> {
	List<Config> load();	
	// 根据名称获取配置信息
	Config selectByCfgName(String cfgName);
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from config")
	Date selectLastRecordTime();
}