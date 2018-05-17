package com.pcbwx.ebes.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.ServiceRelation;

public interface ServiceRelationMapper extends BaseMapper<ServiceRelation>{
	
	/**
	 * 按运单id查找
	 * @param expressId
	 * @return
	 */
	List<ServiceRelation> selectByExpressId(@Param("expressId") Integer expressId);
	
	/**
	 * 按运单id集合查找
	 * @param expressIds
	 * @return
	 */
	List<ServiceRelation> selectByExpressIds(@Param("expressIds") Set<Integer> expressIds);
}