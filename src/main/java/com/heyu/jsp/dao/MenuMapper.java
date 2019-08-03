package com.heyu.jsp.dao;

import java.util.Date;
import java.util.List;

import com.heyu.jsp.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author heyu
 */
@Mapper
@Component
public interface MenuMapper extends BaseMapper<Menu>{
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from menu")
	Date selectLastRecordTime();
	
	List<Menu> load();
}