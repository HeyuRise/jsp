package com.pcbwx.ebes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.Sender;

public interface SenderMapper extends BaseMapper<Sender>{
	
	/**
	 * 按内网Code查找可用发件人
	 * @param innerCode
	 * @return
	 */
	Sender selectByInnerCodeNoStatus(@Param("innerCode") String innerCode);
	
	/**
	 * 按内网Code查找
	 * @param innerCode
	 * @return
	 */
	Sender selectByInnerCode(@Param("innerCode") String innerCode);
	
	/**
	 * 按联系人查找
	 * @param contact
	 * @return
	 */
	List<Sender> selectByContact(@Param("contact") String contact);
}