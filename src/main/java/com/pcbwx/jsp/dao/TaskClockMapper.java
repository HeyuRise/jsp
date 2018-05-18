package com.pcbwx.jsp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.jsp.model.TaskClock;

public interface TaskClockMapper extends BaseMapper<TaskClock>{
	List<TaskClock> selectByTask(@Param("task") String task);
}