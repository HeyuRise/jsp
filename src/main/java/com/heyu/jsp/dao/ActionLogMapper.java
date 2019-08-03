package com.heyu.jsp.dao;

import com.heyu.jsp.model.ActionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author heyu
 */
@Mapper
@Component
public interface ActionLogMapper extends BaseMapper<ActionLog> {

}