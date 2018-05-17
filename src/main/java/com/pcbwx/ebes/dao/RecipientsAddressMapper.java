package com.pcbwx.ebes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.RecipientsAddress;

public interface RecipientsAddressMapper extends BaseMapper<RecipientsAddress> {
	
	/**
	 * 按内网id查找
	 * @param innerId
	 * @return
	 */
	RecipientsAddress selectByInnerId(@Param("innerId") Integer innerId);
	
	/**
	 * 按收件人Code查找
	 * @param recipientsCode
	 * @return
	 */
	Integer deleteByRecipientsCode(@Param("recipientsCode") String recipientsCode);
	
	/**
	 * 按条件查找
	 * @param province
	 * @param city
	 * @param county
	 * @param address
	 * @param recipientCodes
	 * @return
	 */
	List<RecipientsAddress> selectByCodesAndAddress(
			@Param("province") String province, @Param("city") String city,
			@Param("county") String county, @Param("address") String address,
			@Param("recipientCodes") List<String> recipientCodes);
}