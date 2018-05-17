package com.pcbwx.ebes.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.Express;

public interface ExpressMapper extends BaseMapper<Express> {

	/**
	 * 按订单号查找
	 */
	Express selectByOrderId(@Param("orderId") String orderId);

	/**
	 * 按运单号查找
	 */
	Express selectByMailno(@Param("mailNo") String mailNo);

	/**
	 * 按子单号查找
	 */
	Express selectByMailnoChild(@Param("mailnoChild") String mailnoChild);

	/**
	 * 查找请求超时的订单（过时）
	 */
	List<Express> selectTimeOutExpress(@Param("startTime") Date startTime);

	/**
	 * 获取按条件查找个数
	 */
	Integer getSelectConditionSize(@Param("orderId") String orderId,
			@Param("mailno") String mailno,
			@Param("mailnoChild") String mailnoChild,
			@Param("sendCompany") String sendCompany,
			@Param("receiveCompany") String receiveCompany,
			@Param("sendContact") String sendContact,
			@Param("receiveContact") String receiveContact,
			@Param("sendDateBegin") Date sendDateBegin,
			@Param("sendDateEnd") Date sendDateEnd,
			@Param("statues") List<String> statues,
			@Param("expressIds") Set<Integer> expressIds);
	
	/**
	 * 按条件查找
	 */
	List<Express> selectByCondition(@Param("orderId") String orderId,
			@Param("mailno") String mailno,
			@Param("mailnoChild") String mailnoChild,
			@Param("sendCompany") String sendCompany,
			@Param("receiveCompany") String receiveCompany,
			@Param("sendContact") String sendContact,
			@Param("receiveContact") String receiveContact,
			@Param("sendDateBegin") Date sendDateBegin,
			@Param("sendDateEnd") Date sendDateEnd,
			@Param("statues") List<String> statues,
			@Param("expressIds") Set<Integer> expressIds,
			@Param("start") Integer start,
			@Param("size") Integer size);

	/**
	 * 按运单id集合查找
	 * @param expressIds
	 * @return
	 */
	List<Express> selectSynMailno(@Param("expressIds") Set<Integer> expressIds);
	
	/**
	 * 查找没有运费的已完成的单子
	 * @param status 状态集合
	 * @return
	 */
	List<Express> getNoMailnoInfos(@Param("statues") Set<String> statues);
}