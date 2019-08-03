package com.heyu.jsp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.heyu.jsp.model.Log;

/**
 * @author heyu
 */
@Mapper
@Component
public interface LogMapper extends BaseMapper<Log> {

}