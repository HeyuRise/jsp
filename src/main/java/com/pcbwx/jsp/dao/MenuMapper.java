package com.pcbwx.jsp.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.jsp.model.Menu;

public interface MenuMapper extends BaseMapper<Menu>{
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from menu")
	Date selectLastRecordTime();
	
	List<Menu> load();
}