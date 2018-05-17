package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.RoutePush;

public interface RoutePushMapper extends BaseMapper<RoutePush> {
	/**
	 * 按运单号查找
	 * @param mailno
	 * @return
	 */
	List<RoutePush> selectByMailno(@Param("mailNo") String mailno);

	/**
	 * 按路由操作码和开始结束时间查找
	 * @param opCodes
	 * @param dateBegin
	 * @param dateEnd
	 * @return
	 */
	List<RoutePush> selectByOpcodesAndTime(
			@Param("opCodes") List<String> opCodes,
			@Param("dateBegin") Date dateBegin, @Param("dateEnd") Date dateEnd);

	/**
	 * 按路由操作码和运单id集合查找
	 * @param opCodes
	 * @param expressIds
	 * @return
	 */
	List<RoutePush> selectByOPCodesAndExpressIds(
			@Param("opCodes") List<String> opCodes,
			@Param("expressIds") Set<Integer> expressIds);
	
	/**
	 * 按运单id查找
	 * @param expressId
	 * @return
	 */
	List<RoutePush> selectByExpressId(@Param("expressId") Integer expressId);
	
	/**
	 * 按日期查找（yyyy-MM-dd）
	 * @param dayTime
	 * @return
	 */
	List<RoutePush> selectByCreateTime(@Param("dayTime") String dayTime);
}