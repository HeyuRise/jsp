package com.heyu.jsp.dao;

import java.util.List;

import com.heyu.jsp.model.TaskClock;
import org.apache.ibatis.annotations.Param;

public interface TaskClockMapper extends BaseMapper<TaskClock>{
	List<TaskClock> selectByTask(@Param("task") String task);
}