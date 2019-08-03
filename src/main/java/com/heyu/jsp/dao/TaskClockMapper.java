package com.heyu.jsp.dao;

import java.util.List;

import com.heyu.jsp.model.TaskClock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author heyu
 */
@Mapper
@Component
public interface TaskClockMapper extends BaseMapper<TaskClock>{
	List<TaskClock> selectByTask(@Param("task") String task);
}