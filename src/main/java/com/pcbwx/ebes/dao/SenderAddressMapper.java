package com.pcbwx.ebes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.SenderAddress;

public interface SenderAddressMapper extends BaseMapper<SenderAddress> {
	
	/**
	 * 按内网Id查找可用发件人地址
	 * @param innerId
	 * @return
	 */
	SenderAddress selectByInnerIdNoStatus(@Param("innerId") Integer innerId);

	/**
	 * 按内网Id查找
	 * @param innerId
	 * @return
	 */
	SenderAddress selectByInnerId(@Param("innerId") Integer innerId);
	
	/**
	 * 全部删除
	 * @return
	 */
	Integer deleteAll();
	
	/**
	 * 按条件查找
	 * @param province
	 * @param city
	 * @param county
	 * @param address
	 * @return
	 */
	List<SenderAddress> selectByAddress(@Param("province") String province,
			@Param("city") String city, @Param("county") String county,
			@Param("address") String address);

}