package com.heyu.jsp.dao;

import com.heyu.jsp.model.RecordUtils;

public interface RecordUtilsMapper extends BaseMapper<RecordUtils> {
	int updateByName(RecordUtils record);
	
	RecordUtils selectByName(String recordName);
}