package com.pcbwx.jsp.dao;

import com.pcbwx.jsp.model.RecordUtils;

public interface RecordUtilsMapper extends BaseMapper<RecordUtils> {
	int updateByName(RecordUtils record);
	
	RecordUtils selectByName(String recordName);
}