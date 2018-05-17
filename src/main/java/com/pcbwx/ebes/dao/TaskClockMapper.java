package com.pcbwx.ebes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.TaskClock;

public interface TaskClockMapper extends BaseMapper<TaskClock>{
	List<TaskClock> selectByTask(@Param("task") String task);
}