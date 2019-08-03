package com.heyu.jsp.dao;

import com.heyu.jsp.model.RecordUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author heyu
 */
@Mapper
@Component
public interface RecordUtilsMapper extends BaseMapper<RecordUtils> {
	int updateByName(RecordUtils record);
	
	RecordUtils selectByName(String recordName);
}