package com.pcbwx.ebes.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.ebes.model.Recipients;

public interface RecipientsMapper extends BaseMapper<Recipients>{
	
	/**
	 * 取出全部
	 * @return
	 */
	List<Recipients> load();
	
	/**
	 * 查找包含集合中Code的收件人
	 * @param innerCodes
	 * @return
	 */
	List<Recipients> listByCodes(@Param("innerCodes") List<String> innerCodes);
	
	/**
	 * 按内网Code查找可用的收件人
	 * @param innerCode
	 * @return
	 */
	Recipients selectByInnerCode(@Param("innerCode") String innerCode);
	
	/**
	 * 按内网Code查找
	 * @param innerCode
	 * @return
	 */
	Recipients selectByInnerCodeNoStatus(@Param("innerCode") String innerCode);

	/**
	 * 按公司和名字查找
	 * @param company
	 * @param name
	 * @return
	 */
	List<Recipients> selectByCompanyAndName(@Param("company") String company, @Param("name") String name);
}