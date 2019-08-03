package com.heyu.jsp.dao;

import java.util.Date;
import java.util.List;

import com.heyu.jsp.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author heyu
 */
@Mapper
@Component
public interface DictionaryMapper extends BaseMapper<Dictionary> {
	List<Dictionary> load();
	
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from dictionary")
	Date selectLastRecordTime();
	
	List<Dictionary> loadByType(String type);
}